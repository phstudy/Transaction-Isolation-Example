package org.phstudy;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;
import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

	static Log logger = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws SQLException,
			InterruptedException {

		int[] isolations = { TRANSACTION_READ_UNCOMMITTED,
				TRANSACTION_READ_COMMITTED, TRANSACTION_REPEATABLE_READ,
				TRANSACTION_SERIALIZABLE };
		
		String[] isolations_string = { "TRANSACTION_READ_UNCOMMITTED",
				"TRANSACTION_READ_COMMITTED", "TRANSACTION_REPEATABLE_READ",
				"TRANSACTION_SERIALIZABLE" };

		int cnt = 0;
		for (int isolationType : isolations) {
			logger.info(isolations_string[cnt++] + " test");

			TranscationIsolationTest test = new TranscationIsolationTest();
			test.executeDirtyReadsQuery(isolationType);
			test.executeNonRepeatableReadsQuery(isolationType);
			test.executePhantomReadsQuery(isolationType);
		}
	}
}
