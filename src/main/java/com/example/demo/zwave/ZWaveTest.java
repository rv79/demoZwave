package com.example.demo.zwave;

import com.whizzosoftware.wzwave.controller.ZWaveController;
import com.whizzosoftware.wzwave.controller.ZWaveControllerListener;
import com.whizzosoftware.wzwave.controller.netty.NettyZWaveController;
import com.whizzosoftware.wzwave.node.ZWaveEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ZWaveTest implements ZWaveControllerListener {
	private final Logger logger = LoggerFactory.getLogger(ZWaveTest.class);
	private ZWaveController controller;

	public ZWaveTest() {
		logger.info("Create ZWave controller...");
		controller = new NettyZWaveController("/dev/ttyACM0");
		controller.setListener(this);
		controller.start();
	}

	@Override
	public void onZWaveNodeAdded(ZWaveEndpoint node) {
		System.out.println("Z-Wave node added: " + node.getNodeId());
	}

	@Override
	public void onZWaveNodeUpdated(ZWaveEndpoint node) {
		System.out.println("Z-Wave node updated: " + node.getNodeId());
	}

	@Override
	public void onZWaveConnectionFailure(Throwable t) {
		System.out.println("Something bad happened: " + t);
	}
}