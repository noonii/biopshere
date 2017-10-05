# Instructions

<ul>
<li>Need Eclipse</li>
<li>Need Maven and my pom file for reflections library</li>
</ul>

# Summary

Simple Java program that prints to console. 
<ul>
<li>Loads classes dynamically from packages without knowing names</li>
<li>Data structures, object manipulation, polymorphism, interfaces, enums, instances, reflections, and more.</li>
</ul>

# Scalability

<ul>
<li>When adding resources or inhabitants, you MUST follow the format of the other resource/inhabitant classes for them to function</li>
<li>No further data needs to be entered in those classes</li>
<li>Data must be entered in ServerConstants.java in the same order as you see the classes under their package</li>
<li>Enter in the resources that apply, otherwise don't worry about it!</li>
</ul>

# Example 

<ul>
<li>com.playgon.resources example format</li>
<li>It is necessary to include the empty constructor because of reflections.</li>
</ul>

	public class MoonRocks extends ResourceImpl {
	
		public MoonRocks() {

		}

		/**
		 * Initialize with amount of MoonRocks the organism consumes per day
		 * @param amount
		 */
		public MoonRocks(int amount) {
			setAmount(amount);
		}

		@Override
		public String toString() {
			return this.getClass().getSimpleName() + " x " + getAmount();
		}
	}
