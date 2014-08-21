package vault;

import engine.*;
import projects.fairmont.*;

public class VaultTests {

	public static void main(String[] args) throws Exception {

		BaseTest.setUp();

		try {
			CheckAvailability check = new CheckAvailability();
			check.checkAvailability();
		} finally {
			try {
				NewEnroll newenroll = new NewEnroll();
				newenroll.test();
			} finally {
				DestinationMaps maps = new DestinationMaps();
				maps.checkMaps();
			}
		}

	}
}
