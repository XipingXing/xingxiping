
package org.xxp.fastdfs;

import java.net.InetSocketAddress;

import org.xxp.fastdfs.common.NameValuePair;
import org.xxp.fastdfs.core.ClientGlobal;
import org.xxp.fastdfs.core.StorageClient1;
import org.xxp.fastdfs.core.StorageServer;
import org.xxp.fastdfs.core.TrackerClient;
import org.xxp.fastdfs.core.TrackerGroup;
import org.xxp.fastdfs.core.TrackerServer;

public class Test1
{
  public static void main(String args[])
  {
  	try
  	{
		ClientGlobal.init("fdfs_client.conf");
		System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
		System.out.println("charset=" + ClientGlobal.g_charset);
  		
		TrackerGroup tg = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress("192.168.0.196", 22122)});
		TrackerClient tc = new TrackerClient(tg);
		
		TrackerServer ts = tc.getConnection();
		if (ts == null)
		{
			System.out.println("getConnection return null");
			return;
		}

		StorageServer ss = tc.getStoreStorage(ts);
		if (ss == null)
		{
			System.out.println("getStoreStorage return null");
		}
		
		StorageClient1 sc1 = new StorageClient1(ts, ss);
		
		NameValuePair[] meta_list = null;  //new NameValuePair[0];
		String item = "c:/windows/system32/notepad.exe";
		String fileid = sc1.upload_file1(item, "exe", meta_list); //�����쳣
		
		System.out.println("Upload local file "+item+" ok, fileid="+fileid);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	
	}
}
