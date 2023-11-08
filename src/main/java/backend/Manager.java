package backend;

import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;

public class Manager {
	private static Map<Connection, Manager> managers = null;

	private Connection conn = null;
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;

	public static Manager getManager(Connection conn) {
		if (managers == null)
			managers = new HashMap<>();

		Manager manager = managers.get(conn);
		if (manager == null) {
			manager = new Manager(conn);
			managers.put(conn, manager);
		}
		return manager;
	}

	public static Manager getManager() {
		return getManager(DatabaseConnection.getConnection());
	}

	public Manager(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Creates a CustomerManager object if not already created.
	 * @return Returns the newly or already created CustomerManager object.
	 */
	public CustomerManager getCustomerManager() {
		if (cman == null) {
			cman = new CustomerManager(conn);
		}
		return cman;
	}

	/**
	 * Creates a ReservationManager object if not already created.
	 * @return Returns the newly or already created ReservationManager object.
	 */
	public ReservationManager getReservationManager() {
		if (resman == null) {
			resman = new ReservationManager(conn);
		}
		return resman;
	}

	/**
	 * Creates a RoomManager object if not already created.
	 * @return Returns the newly or already created RoomManager object.
	 */
	public RoomManager getRoomManager() {
		if (rooman == null) {
			rooman = new RoomManager(conn);
		}
		return rooman;
	}

	/**
	 * Creates a RoomTypeManager object if not already created.
	 * @return Returns the newly or already created RoomTypeManager object.
	 */
	public RoomTypeManager getRoomTypeManager() {
		if (rtypeman == null) {
			rtypeman = new RoomTypeManager(conn);
		}
		return rtypeman;
	}

	/**
	 * Creates a BillingManager object if not already created.
	 * @return Returns the newly or already created BillingManager object.
	 */
	public BillingManager getBillingManager() {
		if (bman == null) {
			bman = new BillingManager(conn);
		}
		return bman;
	}
}
