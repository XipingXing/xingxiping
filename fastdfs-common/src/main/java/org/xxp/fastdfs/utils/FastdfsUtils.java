package org.xxp.fastdfs.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.StringUtils;
import org.xxp.fastdfs.common.MyException;
import org.xxp.fastdfs.common.NameValuePair;
import org.xxp.fastdfs.core.StorageClient1;
import org.xxp.fastdfs.core.TrackerServer;
import org.xxp.fastdfs.pool.ConnectionPoolManager;
import org.xxp.fastdfs.pool.IConnectionPool;

/**
 * fastdfs工具类
 * 
 * @author xiping xing
 *
 *         Created on 2016年6月21日
 *
 */
public class FastdfsUtils {

  private static String fdfs_client_conf = "fdfs_client.conf";

  /**
   * 文件上传
   * 
   * @param filePath
   *          文件路径
   * @return
   * @throws MyException
   * @throws IOException
   */
  public static String fastdfsUploadFile(String filePath) throws Exception {
    TrackerServer trackerServer = getConPool().getConnection();
    StorageClient1 client = new StorageClient1(trackerServer, null);

    NameValuePair[] metaList = new NameValuePair[1];
    metaList[0] = new NameValuePair("fileName", filePath);
    String fileId = client.upload_file1(filePath, null, metaList);
    getConPool().releaseConncetion(trackerServer);
    return fileId;
  }

  /**
   * 
   * 文件上传
   * 
   * @param fileBytes
   *          字节
   * @param file_ext_name
   *          文件扩展名，不包括(.)
   * @return
   * @throws Exception
   */
  public static String fastdfsUploadFile(byte[] fileBytes, String file_ext_name) throws Exception {

    if (file_ext_name == null || StringUtils.isEmpty(file_ext_name) || file_ext_name.indexOf(".") > -1) {
      throw new Exception(
          "file_ext_name param couldn't be null, empty or include dot,please make sure! -> " + file_ext_name);
    }

    TrackerServer trackerServer = getConPool().getConnection();
    StorageClient1 client = new StorageClient1(trackerServer, null);

    NameValuePair[] metaList = new NameValuePair[0];
    String fileId = client.upload_file1(fileBytes, file_ext_name, metaList);
    getConPool().releaseConncetion(trackerServer);

    return fileId;
  }

  /**
   * 上传文件
   * 
   * @param in
   *          输入流
   * @param file_ext_name
   *          扩展名
   * @param charsetName
   *          编码，默认UTF-8
   * @return
   * @throws Exception
   */
  public static String fastdfsUploadFile(InputStream in, String file_ext_name, String charsetName) throws Exception {
    if (in == null) {
      throw new Exception("file inputStream couldn't be null!");
    }
    int count = 0;
    while (count == 0) {
      count = in.available();
    }
    byte[] b = new byte[count];
    in.read(b);
    return fastdfsUploadFile(b, file_ext_name);
  }

  /**
   * 删除文件
   * 
   * @param fileId
   *          文件id
   * @return 0 成功，其他失败
   * @throws Exception
   */
  public static int fastdfsDeleteFile(String fileId) throws Exception {

    if (fileId == null || StringUtils.isEmpty(fileId)) {
      throw new Exception("fileId param couldn't be null or empty,please make sure! -> " + fileId);
    }

    TrackerServer trackerServer = getConPool().getConnection();
    StorageClient1 client = new StorageClient1(trackerServer, null);

    int errorCode = client.delete_file1(fileId);
    getConPool().releaseConncetion(trackerServer);
    return errorCode;
  }

  /**
   * 下载文件
   * 
   * @param fileId
   *          文件id
   * @return
   * @throws Exception
   */
  public static byte[] fastdfsDownLoadFile(String fileId) throws Exception {

    if (fileId == null || StringUtils.isEmpty(fileId)) {
      throw new Exception("fileId param couldn't be null or empty,please make sure! -> " + fileId);
    }

    TrackerServer trackerServer = getConPool().getConnection();
    StorageClient1 client = new StorageClient1(trackerServer, null);

    byte[] resFile = client.download_file1(fileId);
    getConPool().releaseConncetion(trackerServer);

    return resFile;

  }

  private static IConnectionPool getConPool() throws Exception {
    ConnectionPoolManager cpm = ConnectionPoolManager.getInstance(fdfs_client_conf);
    IConnectionPool icp = cpm.getPool();
    return icp;
  }

}
