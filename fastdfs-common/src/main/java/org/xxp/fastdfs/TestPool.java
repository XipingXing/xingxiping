package org.xxp.fastdfs;

import org.xxp.fastdfs.utils.FastdfsUtils;

public class TestPool {

  public static void main(String[] args) throws Exception {

    String local_filename = "C:\\Users\\Administrator\\Desktop\\fastDFS\\fastDFS安装.docx";
    
    String[] agrs = {"wKiZgFdnaROAMLx1AAB0Ez8ocaA57.docx"};
    
    for(String s : agrs){
      FastdfsUtils.fastdfsDeleteFile("group1/M00/00/00/" + s);
    }
    
    System.out.println("11111111111");

//    Thread td1 = new Thread(new Runnable() {
//      @Override
//      public void run() {
//
//        try {
//          Thread.sleep(6 * 1000l);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//        try {
//          String id = FastdfsUtils.fastdfsUploadFile(local_filename);
//          System.out.println(id);
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//
//      }
//    });

//    Thread td2 = new Thread(new Runnable() {
//      @Override
//      public void run() {
//        try {
//          File f = new File(local_filename);
//          
//          FastdfsUtils.fastdfsUploadFile(new FileInputStream(f), "docx", "");
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    });

//    Thread td3 = new Thread(new Runnable() {
//      @Override
//      public void run() {
//
//        try {
//          FastdfsUtils.fastdfsDeleteFile(local_filename);
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    });

//    td1.start();
//    td2.start();
//    td3.start();

  }

}
