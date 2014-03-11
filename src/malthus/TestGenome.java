import java.util.*;

public class TestGenome {
	private static enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
		public static final Day VALUES[] = values();
		public int getValue() {
			return ordinal();
		}
	}


	private static class Gene1 extends Gene<Integer> {
		protected Integer randomize() {
			Random rand = new Random();
			return rand.nextInt();
		}


		public double getValue() {
			return (double) this.get();
		}

	}


	private static class Gene2 extends Gene<Day> {
		protected Day randomize() {
			Random rand = new Random();
			return Day.VALUES[rand.nextInt(Day.VALUES.length)];
		}

		
		public double getValue() {
			return (double) this.get().getValue();
		}
	}


	public static void main(String[] args) {
		Gene1 g1_1 = new Gene1();
		System.out.println(g1_1.get());


		Gene2 g2_1 = new Gene2();
		System.out.println(g2_1.get());

		Vector<Gene> v = new Vector<Gene>();
	}
}
