class Main {
	public static void main(String[] args) {
		System.out.println("Starting...");
		PaymentInfoManager pm = new PaymentInfoManager();
		CustomerManager cm = new CustomerManager();
		ReservationManager rm = new ReservationManager();
		Gui ui = new Gui(rm);
	}
}
