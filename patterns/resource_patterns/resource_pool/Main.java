package resource_patterns.resource_pool;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var pool = ConnectionPool.getInstance();
        var conn1 = pool.getPooledConnection();
        System.out.println(pool);

        conn1.close();
        System.out.println(pool);
        var conns = new ArrayList<PooledConnection>();
        IntStream.range(0, 10).forEachOrdered(it -> {
            conns.add(pool.getPooledConnection());
        });

        IntStream.range(0, 5).forEachOrdered(it -> {
            var conn = conns.get(it);
            conn.close();
        });
        System.out.println(pool);
    }
}
