
public class Model {
		private int name;		
		private Model(int i){
			this.name = i;
			System.out.println(this.name);
		};	
		public static void main(String[] args) {
			Model model = new Model(20);
		}
};