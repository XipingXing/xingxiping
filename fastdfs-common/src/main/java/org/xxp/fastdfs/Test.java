/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
**/

package org.xxp.fastdfs;

import org.xxp.fastdfs.common.NameValuePair;
import org.xxp.fastdfs.core.ClientGlobal;
import org.xxp.fastdfs.core.StorageClient1;
import org.xxp.fastdfs.core.StorageServer;
import org.xxp.fastdfs.core.TrackerClient;
import org.xxp.fastdfs.core.TrackerServer;

/**
* client test
* @author Happy Fish / YuQing
* @version Version 1.18
*/
public class Test
{
	private Test()
	{
	}
	
	/**
	* entry point
	* @param args comand arguments
	*     <ul><li>args[0]: config filename</li></ul>
	*     <ul><li>args[1]: local filename to upload</li></ul>
	*/
  public static void main(String args[])
  {
//  	if (args.length < 2)
//  	{
//  		System.out.println("Error: Must have 2 parameters, one is config filename, "
//  		                 + "the other is the local filename to upload");
//  		return;
//  	}
//  	
//  	System.out.println("java.version=" + System.getProperty("java.version"));
  	  
  	String conf_filename = "config/fdfs_client.conf";
  	String local_filename = "C:\\Users\\Administrator\\Downloads\\Gradle Plugin User Guide中文版版本.pdf";
  	
  	try
  	{
  		ClientGlobal.init(conf_filename);
  		System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
  		System.out.println("charset=" + ClientGlobal.g_charset);
 
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("fileName", local_filename);
        String fileId = client.upload_file1(local_filename, null, metaList);
        System.out.println("upload success. file id is: " + fileId);

        int i = 0;
        while (i++ < 10) {
                byte[] result = client.download_file1(fileId);
                System.out.println(i + ", download result is: " + result.length);
        }
        
  		trackerServer.close();
  	}
  	catch(Exception ex)
  	{
  		ex.printStackTrace();
  	}
  }
}
