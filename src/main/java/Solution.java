import java.io.*;
import java.util.*;

public class Solution {
    private final int n;
    private final String source;
    private final String target;

    private Map<String, List<String>> distanceMap;
    private Queue<String> configurationsToProcess;

private static int count;
    public static Solution readData() {
        return readData(null);
    }

    public static Solution readData(String resourceName) {
        InputStream is;
        if (resourceName != null) {
            is = Solution.class.getClassLoader().getResourceAsStream(resourceName);
        } else {
            is = System.in;
        }

        try (Scanner scanner = new Scanner(is)) {
            int n = Integer.parseInt(scanner.nextLine().trim());
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                sb.append(scanner.nextLine().trim());
            }
            String source = sb.toString();
            sb.setLength(0);

            for (int i = 0; i < n; i++) {
                sb.append(scanner.nextLine().trim());
            }
            String target = sb.toString();

            return new Solution(n, source, target);
        }

    }

    public Solution(int n, String source, String target) {
        this.n = n;
        this.source = source;
        this.target = target;
    }

    public long solve() {
        configurationsToProcess = new LinkedList<>();
        distanceMap = new HashMap<>();
        configurationsToProcess.add(source + ",");
        traverse();

        long solution = sumChecksums(distanceMap.get(target));
        return solution;
    }

    protected long calcChecksum(String path) {
        long checksum = 0;

        for (char move : path.toCharArray()) {
            checksum = (checksum * 243 + move) % 100_000_007L;
        }
        return checksum;
    }

    protected long sumChecksums(List<String> paths) {
        long sumChecksum = 0;

        for (String path : paths) {
            sumChecksum += calcChecksum(path);
        }
        return sumChecksum % 100_000_007L;
    }


    private void traverse() {
        while (!configurationsToProcess.isEmpty()) {
            count++;
            String currentPoint = configurationsToProcess.remove();
            String[] parts = currentPoint.split(",");
            String currentConfiguration = parts[0];
            String currentPath = parts.length > 1 ? parts[1] : "";

            // current path is longer than existing from the source to the target
            if (distanceMap.containsKey(target)
                    && distanceMap.get(target).get(0).length() < currentPath.length()) {
                configurationsToProcess.clear();
                continue;
            }

            // we have reached target configuration so we do not need to check next step from this point
            if (currentConfiguration.equals(target)) {
                updateDistanceMap(currentConfiguration, currentPath);
                continue;
            }

            updateDistanceMap(currentConfiguration, currentPath);
            int holePosition = currentConfiguration.indexOf("W");
            moveUp(currentConfiguration, currentPath, holePosition);
            moveDown(currentConfiguration, currentPath, holePosition);
            moveLeft(currentConfiguration, currentPath, holePosition);
            moveRight(currentConfiguration, currentPath, holePosition);
        }
    }

    //returns false if we got worse result
    private void updateDistanceMap(String configuration, String currentPath) {
        List<String> paths = distanceMap.computeIfAbsent(configuration, (k) -> new ArrayList<>());

        if (!paths.isEmpty() && paths.get(0).length() > currentPath.length()) {
            paths.clear();
        }
        paths.add(currentPath);
    }

    private void moveUp(String currentConfiguration, String currentPath, int holePosition) {
        if (holePosition <= n * (n - 1) - 1) {
            move(currentConfiguration, currentPath + 'U', holePosition, holePosition + n);
        }
    }

    private void moveDown(String currentConfiguration, String currentPath, int holePosition) {
        if (holePosition >= n) {
            move(currentConfiguration, currentPath + 'D', holePosition, holePosition - n);
        }
    }

    private void moveLeft(String currentConfiguration, String currentPath, int holePosition) {
        if (holePosition % n != n - 1) {
            move(currentConfiguration, currentPath + 'L', holePosition, holePosition + 1);
        }
    }

    private void moveRight(String currentConfiguration, String currentPath, int holePosition) {
        if (holePosition % n != 0) {
            move(currentConfiguration, currentPath + 'R', holePosition, holePosition - 1);
        }
    }

    private void move(String currentConfiguration, String newPath, int holePosition, int newPosition) {
        String newConfiguration = swapCharacters(currentConfiguration, holePosition, newPosition);

        // current path is longer than existing from the source to this configuration
        if (!distanceMap.containsKey(newConfiguration)
                || distanceMap.get(newConfiguration).get(0).length() >= newPath.length()) {
            configurationsToProcess.add(newConfiguration + "," + newPath);
        }
    }

    private String swapCharacters(String currentConfiguration, int from, int to) {
        StringBuilder currentConfigurationSb = new StringBuilder(currentConfiguration);
        char fromChar = currentConfigurationSb.charAt(from);
        char toChar = currentConfigurationSb.charAt(to);
        currentConfigurationSb.setCharAt(from, toChar);
        currentConfigurationSb.setCharAt(to, fromChar);

        return currentConfigurationSb.toString();
    }

    public static void main(String[] args) {
        Solution solution = readData("sample3.txt");
        System.out.println(solution.solve());
    }
}