# code-note
常见的一些代码片断

1. 把 Bitmap 转换成 File 的方法  


```
public void saveFile(Bitmap bm, String fileName) throws IOException {   
  String path = getSDPath() +"/revoeye/";       
     File dirFile = new File(path);    
     if(!dirFile.exists()){    
         dirFile.mkdir();    
     }    
     File myCaptureFile = new File(path + fileName);    
     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));    
     bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);    
     bos.flush();    
     bos.close();    
 }  
 ```
