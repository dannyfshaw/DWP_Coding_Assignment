package com.dwp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dwp.model.NotificationForQueue;

@Service
public class MongoDBServiceImpl implements MongoDBService {
	private static final Logger logger = LoggerFactory.getLogger(MongoDBServiceImpl.class);

	@Override
	public boolean saveMessageToMongoDB(NotificationForQueue notificationForQueue) {
		logger.info("MongoDBService NOT YET IMPLEMENTED");
		// FIXME Add implementation to save message to MongoDB
		return true;
	}

}
