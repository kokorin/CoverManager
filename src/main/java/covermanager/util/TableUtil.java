package covermanager.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class TableUtil {
    private TableUtil(){}

    public static final Callback<TableView.ResizeFeatures<?>, Boolean> PERCENTAGE_RESIZE_POLICY = new Callback<TableView.ResizeFeatures<?>, Boolean>() {
        private final Map<TableView<?>, Double> previousTableWidth = new WeakHashMap<>();
        private final Map<TableView<?>, Integer> previousColumnCount = new WeakHashMap<>();

        @Override
        public Boolean call(TableView.ResizeFeatures<?> param) {
            TableView<?> table = param.getTable();
            TableColumn<?,?> column = param.getColumn();
            double delta = param.getDelta();

            List<? extends TableColumn<?, ?>> columns = table.getVisibleLeafColumns();
            double totalWidth = columns.stream().mapToDouble(TableColumn::getWidth).sum();
            double totalPrefWidth = columns.stream().mapToDouble(TableColumn::getPrefWidth).sum();

            if (totalPrefWidth < 1.0) {
                return false;
            }

            if (column != null) {
                column.impl_setWidth(column.getWidth() + delta);
                double totalDistributionWidth = totalWidth - column.getWidth();

                for (TableColumn<?, ?> c : columns) {
                    if (c.equals(column)) {
                        continue;
                    }

                    double columnDelta = delta * c.getWidth() / totalDistributionWidth;
                    c.impl_setWidth(c.getWidth() - columnDelta);
                }

                return true;
            }

            Double prevTableWidth = previousTableWidth.get(table);
            Integer prevColumnCount = previousColumnCount.get(table);
            previousTableWidth.put(table, table.getWidth());
            previousColumnCount.put(table, columns.size());

            /* Distribute exclusively by PrefWidth if
             * - Table just created or has no width (possibly it's the same)
             * - Column added or removed */
            if (prevTableWidth == null || prevTableWidth == 0 || prevColumnCount == null
                    || !prevColumnCount.equals(columns.size()))
            {
                for (TableColumn<?, ?> c : columns) {
                    c.impl_setWidth(table.getWidth() * c.getPrefWidth() / totalPrefWidth);
                }
                return true;
            }

            double tableDelta = table.getWidth() - prevTableWidth;
            for (TableColumn<?, ?> c : columns) {
                double columnDelta = tableDelta * c.getWidth() / totalWidth;
                c.impl_setWidth(c.getWidth() + columnDelta);
            }

            return true;
        }

        @Override
        public String toString() {
            return "percentage-resize";
        }
    };
}
