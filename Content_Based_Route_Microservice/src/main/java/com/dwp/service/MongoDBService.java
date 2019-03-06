package com.dwp.service;

import com.dwp.model.NotificationForQueue;

public interface MongoDBService {

	public boolean saveMessageToMongoDB(NotificationForQueue notificationForQueue);

}
