import java.util.Arrays;

public class ReorderLogFiles {
	public String[] reorderLogFiles(String[] logs) {
		if (logs == null || logs.length == 0) {
			return new String[0];
		}
		Arrays.sort(logs, (log1, log2) -> {
			String[] words1 = log1.split(" ", 2);
			String[] words2 = log2.split(" ", 2);
			boolean isDigit1 = Character.isDigit(words1[1].charAt(0));
			boolean isDigit2 = Character.isDigit(words2[1].charAt(0));
			if (!isDigit1 && !isDigit2) {
				int comp = words1[1].compareTo(words2[1]);
				if (comp != 0) {
					return comp;
				}
				return words1[0].compareTo(words2[0]);
			}
			return isDigit1 ? (isDigit2 ? 0 : 1) : -1;

		});
		return logs;
	}
	// s1 is string return -1 front
	// s1 is digital if s2 is not digital s1 should behind s2 1
	
	public static void main(String[] args) {
		System.out.println("a".compareTo("b"));
	}
}
