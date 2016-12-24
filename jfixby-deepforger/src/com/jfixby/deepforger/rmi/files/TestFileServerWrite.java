
package com.jfixby.deepforger.rmi.files;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.rmi.client.files.RMIFileSystem;
import com.jfixby.scarabei.rmi.client.files.RMIFileSystemConfig;

public class TestFileServerWrite {
	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();
		Json.installComponent("com.jfixby.cmns.adopted.gdx.json.RedJson");

		final File config_file = LocalFileSystem.ApplicationHome().child("rmi-file-server.cfg");
		final ByteArray data = config_file.readBytes();

		final String string = JUtils.newString(data);
		L.d("Connecting to remote file syste", string);

		final ServerConfig config = Json.deserializeFromString(ServerConfig.class, string);

		final RMIFileSystemConfig client_config = new RMIFileSystemConfig();
		client_config.setRemoteHost(config.remote_host);
		client_config.setRemotePort(config.port);
		client_config.setRemoteBox(config.box_name);

		final RMIFileSystem remote_file_system = new RMIFileSystem(client_config);
		remote_file_system.ping();

		remote_file_system.ROOT().listDirectChildren().print("scan root");

		final File home = remote_file_system.ROOT();
		final File file = home.child("1");
		L.d("delete " + file, file.delete());

		home.getFileSystem().copyFolderContentsToFolder(LocalFileSystem.newFile("D:\\[SHIT]"), home);

	}
}
