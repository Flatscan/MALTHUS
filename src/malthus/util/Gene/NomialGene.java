public class NomialGene<T extends Enum<T>> extends Gene<T> {
	protected T randomize() {
		Random rand = (Random) this.conf.get("random");
		return T.VALUES[rand.nextInt(T.VALUES.length)];
	}


	public double getValue() {
		return (double) this.get().getValue();
	}
}
