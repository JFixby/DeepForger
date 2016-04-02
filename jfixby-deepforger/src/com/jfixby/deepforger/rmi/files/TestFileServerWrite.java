package com.jfixby.deepforger.rmi.files;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.GdxJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.desktop.DesktopAssembler;
import com.jfixby.rmi.client.files.RMIFileSystem;
import com.jfixby.rmi.client.files.RMIFileSystemConfig;

public class TestFileServerWrite {
    public static void main(String[] args) throws IOException {
	DesktopAssembler.setup();
	Json.installComponent(new GdxJson());

	File config_file = LocalFileSystem.ApplicationHome().child("rmi-file-server.cfg");
	ByteArray data = config_file.readBytes();

	String string = JUtils.newString(data);
	L.d("Connecting to remote file syste", string);

	ServerConfig config = Json.deserializeFromString(ServerConfig.class, string);

	RMIFileSystemConfig client_config = new RMIFileSystemConfig();
	client_config.setRemoteHost(config.remote_host);
	client_config.setRemotePort(config.port);
	client_config.setRemoteBox(config.box_name);

	RMIFileSystem remote_file_system = new RMIFileSystem(client_config);
	remote_file_system.ping();

	remote_file_system.ROOT().listChildren().print("scan root");

	File home = remote_file_system.ROOT();
	File file = home.child("1");
	L.d("delete " + file, file.delete());

	home.getFileSystem().copyFolderContentsToFolder(LocalFileSystem.newFile("D:\\[SHIT]"), home);

    }
}
