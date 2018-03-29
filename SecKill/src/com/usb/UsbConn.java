package com.usb;

import java.util.List;

import javax.usb.UsbConfiguration;
import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbEndpoint;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbInterfacePolicy;
import javax.usb.UsbPipe;
import javax.usb.event.UsbPipeDataEvent;
import javax.usb.event.UsbPipeErrorEvent;
import javax.usb.event.UsbPipeListener;

public class UsbConn {

	private static final int VENDOR_ID = 0x413C;
	/** The product ID of the missile launcher. */
	private static final int PRODUCT_ID = 0x2105;
	// private static final short VENDOR_ID = 0x10c4;
	// // /** The product ID of the missile launcher. */
	// private static final short PRODUCT_ID = -5536;
	private static UsbPipe pipe81, pipe01;

	/**
	 * 依据VID和PID找到设备device
	 * 
	 * @param hub
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static UsbDevice findMissileLauncher(UsbHub hub) {
		UsbDevice launcher = null;

		for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if (device.isUsbHub()) {
				launcher = findMissileLauncher((UsbHub) device);
				if (launcher != null)
					return launcher;
			} else {
				UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
				System.out.println(desc);
				if (desc.idVendor() == VENDOR_ID && (desc.idProduct() & 0x0FFFF) == PRODUCT_ID) {
					System.out.println("找到设备：" + device);
					return device;
				}
			}
		}
		return null;
	}

	public static void sendMessage(UsbDevice device, byte[] message) throws UsbException {
		UsbControlIrp irp = device.createUsbControlIrp(
				(byte) (UsbConst.REQUESTTYPE_TYPE_CLASS | UsbConst.REQUESTTYPE_RECIPIENT_INTERFACE), (byte) 0x09,
				(short) 2, (short) 1);
		irp.setData(message);
		device.syncSubmit(irp);
	}

	/**
	 * 注意权限的配置问题，在linux下可能无法打开device，解决办法参考官方的FAQ
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsbDevice device;
		try {
			device = findMissileLauncher(UsbHostManager.getUsbServices().getRootUsbHub());
			if (device == null) {
				System.out.println("Missile launcher not found.");
				System.exit(1);
				return;
			}
			UsbConfiguration configuration = device.getActiveUsbConfiguration();// 获取配置信息
			UsbInterface iface = configuration.getUsbInterface((byte) 0);// 接口
			iface.claim();
//			iface.claim(new UsbInterfacePolicy() {
//
//				@Override
//				public boolean forceClaim(UsbInterface arg0) {
//					// TODO Auto-generated method stub
//					return true;
//				}
//			});
			for (UsbEndpoint endpoints : (List<UsbEndpoint>) iface.getUsbEndpoints()) {
				System.out.println("--->" + endpoints.getUsbEndpointDescriptor());
			}
			UsbEndpoint endpoint81 = iface.getUsbEndpoint((byte) 0x81);// 接受数据地址
			UsbEndpoint endpoint01 = iface.getUsbEndpoint((byte) 0x04);// 发送数据地址
			pipe81 = endpoint81.getUsbPipe();
			pipe81.open();
//			pipe01 = endpoint01.getUsbPipe();
//			pipe01.open();
//			byte[] dataSend = { (byte) 0x00 };// 需要发送的数据
//			pipe01.asyncSubmit(dataSend);
			pipe81.addUsbPipeListener(new UsbPipeListener() {

				@Override
				public void errorEventOccurred(UsbPipeErrorEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(arg0);
				}

				@Override
				public void dataEventOccurred(UsbPipeDataEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(new String(arg0.getData()));
				}
			});
			 pipe81.close();
			iface.release();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//
		}
	}
}
