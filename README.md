# code-note
常见的一些代码片断

1. 把 Bitmap 转换成 File 的方法  

		public void saveFile(Bitmap bm, String fileName) throws IOException {   
  		String path = getSDPath() +"/revoeye/";       
     	File dirFile = new File(path);    
     	if(!dirFile.exists()){    
       	  dirFile.mkdir();    
    	 }    
     	File myCaptureFile = new File(path + fileName);    
    	BufferedOutputStream bos = new BufferedOutputStream(new  		FileOutputStream(myCaptureFile));    
     	bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);    
     	bos.flush();    
    	bos.close();    
		}  

 
2. 获取 SD 卡的根目录
   
 		public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);// 判断 sd 卡是否存在
        if (sdCardExist){
            sdDir = Environment.getExternalStorageDirectory();// 获取根目录
        	}
        	return sdDir.toString();
   		 }


