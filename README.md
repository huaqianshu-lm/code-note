# code-note
常用的方法

1. Bipmap 转换成 File 的方法

   ```java
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
   ```

2. 获取 SD 卡的根目录

   ```java
   public static String getSDPath() {
       File sdDir = null;
       boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断 sd 卡是否存在
       if (sdCardExist) {
           sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
       }
       return sdDir.toString();
   }
   ```

3. 遍历 set 的方法 

    ​

    ```java
    //迭代遍历    
    Set<String> set = new HashSet<String>();
    Iterator<String> it = set.iterator();
    while (it.hasNext()) {
         String str = it.next();
         System.out.println(str);
     }
    //for 循环遍历：
    for (String str: set) {
       System.out.println(str);
    }

    //当 set 中存放的是 object 的时候：
    Set<Object> set = new HashSet<Object>();
            for (Object obj : set) {
                if (obj instanceof Integer) {
                    int aa = (Integer) obj;
                } else if (obj instanceof String) {
                    String aa = (String) obj
                }
            }
            
    ```



4. 根据经纬度获取两点之间的距离

   ```java
   public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
           float[] results = new float[1];
           Location.distanceBetween(lat1, lon1, lat2, lon2, results);
           return results[0];
     }
   ```



5. 判断是否是手机号码

   ```java
   public static boolean isPhoneNumberValid(String phoneNumber) {
   boolean isValid = false;
   String expression = "^\(?(\d{3})\)?[- ]?(\d{4})[- ]?(\d{4})$";
   CharSequence inputStr = phoneNumber;
   Pattern pattern = Pattern.compile(expression);
   Matcher matcher = pattern.matcher(inputStr);
   if (matcher.matches()) {
       isValid = true;
   }
   return isValid;
   }
   ```

6. 验证用户名

     ```java
     public static boolean isUserNameValid1(String userName) {
     boolean isValid = false;
     String expression1 = "^[a-zA-Z0-9]{4,16}";
     CharSequence inpustr = userName;
     Pattern pattern = Pattern.compile(expression1);
     Matcher matcher = pattern.matcher(inpustr);
     if (matcher.matches()) {
         isValid = true;
     }
     return isValid;
     }
     ```
     ​
     ```java
     public static boolean isUserNameValid2(String userName) { 
         boolean isValid = false;
         String expression2 = "^[1-9]\d*|0$";
         CharSequence inpustr = userName;
         Pattern pattern = Pattern.compile(expression2);
         Matcher matcher = pattern.matcher(inpustr);
         if (matcher.matches()) {
             isValid = true;
         }
         return isValid;
     }
     ```

7. 验证密码
  ​  
  ```java
  public static boolean isPasswordValid(String passwrod) {
   boolean isValid = false;
   String expression2 = "^[A-Za-z0-9\\_]{6,16}$";
   CharSequence inpustr = passwrod;
   Pattern pattern = Pattern.compile(expression2);
   Matcher matcher = pattern.matcher(inpustr);
   if (matcher.matches()) {
        isValid = true;
   }
   return isValid;
   }
  ```

8. 正则表达式判断邮箱格式是否正确

   ```java
    public static boolean isEmail(String email) {
    String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    Pattern p = Pattern.compile(str);
    Matcher m = p.matcher(email);
    return m.matches();
    }
   ```

9. 判断是否是 url

   ```java
   public static boolean isUrl(String str) {

   boolean isValid = false;

   String expression = "http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?";

   CharSequence inputStr = str;

   Pattern pattern = Pattern.compile(expression);

   Matcher matcher = pattern.matcher(inputStr);

   if (matcher.matches()) {

   isValid = true;
   }

   return isValid;

   }
   ```



10. 设置hint大小

  ```java
  public static SpannableString setHintSize(String hint, int size) 	{

   	SpannableString ss = new SpannableString(hint);

   	AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);

   	ss.setSpan(ass, 0, ss.length(), 			Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

   	SpannableString spannableString = new SpannableString(ss);

   	return spannableString;

  }	
  ```

11. 将时间戳转为代表"距现在多久之前"的字符串

     ```java
     public static String getStandardDate(String timeStr) {
            String ts = getTime(timeStr);
            StringBuffer sb = new StringBuffer();
            long t = Long.parseLong(ts);
            long time = System.currentTimeMillis() - (t * 1000);
            long mill = (long) Math.ceil(time / 1000);// 秒前
            long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前

            long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时

            long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前

            if (day - 1 > 0) {
                sb.append(day - 1 + "天");
            } else if (hour - 1 > 0) {
                if (hour >= 24) {
                    sb.append("1天");
                } else {
                    sb.append(hour + "小时");
                }
            } else if (minute - 1 > 0) {
                if (minute == 60) {
                    sb.append("1小时");
                } else {
                    sb.append(minute + "分钟");
                }
            } else if (mill - 1 > 0) {
                if (mill == 60) {
                    sb.append("1分钟");
                } else {
                    sb.append(mill + "秒");
                }
            } else {

                sb.append("刚刚");

            }

            if (!sb.toString().equals("刚刚")) {

                sb.append("前");

            }

            return sb.toString();

        }

        public static String getTime(String user_time) {

            String re_time = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date d;

            try {

                d = sdf.parse(user_time);
                long l = d.getTime();
                String str = String.valueOf(l);
                re_time = str.substring(0, 10);

            } catch (ParseException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            return re_time;

        }

        public static String getBetweenTime(String nowTime, String afterTime) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {

                Date d1 = df.parse(nowTime);
                Date d2 = df.parse(afterTime);
                long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别

                long days = diff / (1000 * 60 * 60 * 24);
                long hours = (diff - days * (1000 * 60 * 60 * 24))
                        / (1000 * 60 * 60);
                long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
                        * (1000 * 60 * 60))
                        / (1000 * 60);
                StringBuffer buffer = new StringBuffer();
                if (days != 0) {
                    if (days > 3) {
                        buffer.append(afterTime);
                    } else {
                        buffer.append(days + "天前");
                    }

                } else if (days == 0 && hours != 0) {
                    buffer.append(hours + "小时前");
                } else if (days == 0 && hours == 0 && minutes != 0) {
                    if (minutes < 3) {
                        buffer.append("刚刚");
                    } else {
                        buffer.append(minutes + "秒前");
                    }

                }
                return buffer.toString();

            } catch (Exception e) {

                return afterTime;

            }

        } 	
     ```

12. 验证身份证号

    ```java
    public static boolean isIDCardNumberValid(String id_card) {
        boolean isValid = false;
        String expression = "^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2])) (([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$";
        CharSequence inputStr = id_card;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    ```

13. 比较两个时间大小

    ```java
    public static int compare_date(String date1, String date2) {

        if (date1 == null || date1.equals(""))

            return 0;

        if (date2 == null || date2.equals(""))

            return 0;

        // 转换为标准时间

        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        Date mydate = null;

        try {

            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);

        } catch (Exception e) {

        }

        long day = mydate.getTime() - date.getTime();

        if (day >= 0)

            return 1;

        else return 0;
    }
    ```

14. 禁止连续点击

    ```java
    public abstract class NoDoubleClickListener implements View.OnClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            }

        }

        public abstract void onNoDoubleClick(View v);
    }
    ```

15. 通过比例设置图片的位置

    ```java
    // 屏幕的宽高
    DisplayMetrics metric = new DisplayMetrics();
    int width = metric.widthPixels;     // 屏幕宽度（像素）
    Bitmap bitmap = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.game_rank_head_bg);
    int bgWidth = bitmap.getWidth();
    int bgHeigth = bitmap.getHeight();
    int height = width * bgHeigth / bgWidth;   // 屏幕高度（像素）
    int x = (int) (width * 0.62);
    int y = (int) (height * 0.36);
    int imgWidth = (int) (width * 0.198);
    ViewGroup.LayoutParams layoutParams1 = headBgIv.getLayoutParams();
    layoutParams1.height =height;
    headBgIv.setLayoutParams(layoutParams1);
    ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(userPhoto.getLayoutParams());
                                margin.setMargins(x,y,0,0);

    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
    layoutParams.height =imgWidth;
    layoutParams.width =imgWidth;
    no1UserPhoto.setLayoutParams(layoutParams);
    ```

16. Toast 禁止连续点击显示多个重复消息

    ```java
    public class ShowToastUtil {
        private static Toast toast;

        public static void showToastOne(Context context, int content) {
            if (toast == null) {
                toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                toast.setText(content);
            }
            toast.show();
        }

        public static void showToastOne(Context context, String content) {
            if (toast == null) {
                toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                toast.setText(content);
            }
            toast.show();
        }
    }
    ```

17. 保存图片

    ```java
      /***
         * 保存图片
         */
        class SaveImage extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String result = "";
                try {
                    String sdcard = Environment.getExternalStorageDirectory().toString();
                    File file = new File(sdcard);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String fileName = new Date().getTime() + ".jpg";
                    file = new File(sdcard + "/" + fileName);
                    InputStream inputStream = null;
                    URL url = new URL(params[0]);
                    Log.e(TAG, "url:" + url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(20000);
                    if (conn.getResponseCode() == 200) {
                        inputStream = conn.getInputStream();
                    }
                    byte[] buffer = new byte[4096];
                    int len = 0;
                    FileOutputStream outStream = new FileOutputStream(file);
                    while ((len = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, len);
                    }
                    outStream.close();
                    MediaStore.Images.Media.insertImage(getContentResolver(),
                            file.getAbsolutePath(), fileName, null);
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/");
    //                Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了
                    result = "图片已保存至：" + file.getAbsolutePath();

                } catch (Exception e) {
                    result = "保存失败！" + e.getLocalizedMessage();
                    Log.e(TAG, result);
                }
                return result;
            }
     
            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(WebActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    ```

18. 截屏生成bitmap

    ```java
     public Bitmap snapshotView(View view) {
    //        if (window != null) {
            //找到当前页面的根布局
    //            View view = window.getDecorView().getRootView();
            //获取当前屏幕的大小
            int width = view.getWidth();
            int height = view.getHeight();
     
            //设置缓存
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
                /*1、从缓存中获取当前屏幕的图片,创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
                 如果直接是控件调用buildDrawingCache
                 *是该控件当前显示在屏幕上的部分就不用减去状态栏的高度了
                 */
            Bitmap temBitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, 标题栏的高度, width, height - 标题栏的高度);
            //禁用DrawingCahce否则会影响性能 ,而且不禁止会导致每次截图到保存的是缓存的位图
            view.destroyDrawingCache();
            view.setDrawingCacheEnabled(false);
     
            return temBitmap;
    //        }
    //        return null;
        }
    ```

19. 扫描二维码

    ```java
     private Result getQRcode(Bitmap bitmap) {
    //        Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
       // 直接从 imageview 控件里取 bitmap
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] data = new int[width * height];
            bitmap.getPixels(data, 0, width, 0, 0, width, height);
            RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            QRCodeReader reader = new QRCodeReader();
            Result result = null;
            try {
                result = reader.decode(binaryBitmap);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (ChecksumException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
     
    //        dealQRCode(result);
     
            return result;// result.getText()获取到扫描结果
        }
    ```

20. 用自带 API 获取 location 对象

    ```java
    /**
         * 获取 location ，经纬度
         */
        public void getLocation() {
            LocationManager locationManager;
            //实例化一个LocationManager对象
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //provider的类型
            String provider = LocationManager.NETWORK_PROVIDER;

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);   //高精度
            criteria.setAltitudeRequired(false);    //不要求海拔
            criteria.setBearingRequired(false); //不要求方位
            criteria.setCostAllowed(false); //不允许有话费
            criteria.setPowerRequirement(Criteria.POWER_LOW);   //低功耗
          if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //通过最后一次的地理位置来获得Location对象
            android.location.Location location = locationManager.getLastKnownLocation(provider);
            Log.e("MainActivity", "log" + location);
            
            /*
             * 第二个参数表示更新的周期，单位为毫秒；第三个参数的含义表示最小距离间隔，单位是米
             * 设定每30秒进行一次自动定位
             */
            locationManager.requestLocationUpdates(provider, 30000, 50,
                    locationListener);
            //移除监听器，在只有一个widget的时候，这个还是适用的
            locationManager.removeUpdates(locationListener);

        } 
          
        
        /**
         * 方位改变时触发，进行调用,在各个方法中执行对应的操作
         */
        private final static LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
            }

            public void onProviderDisabled(String provider) {
            }
          
          
         public void onProviderEnabled(String provider) {
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        }; 
    ```


21. 根据经纬度获取两点之间的距离

    ```java
    private static final double EARTH_RADIUS = 6378.137;

        private static double rad(double d) {
            return d * Math.PI / 180.0;
        }

        // 返回单位是:千米
        public static double getDistance(double longitude1, double latitude1,
                                         double longitude2, double latitude2) {
            double Lat1 = rad(latitude1);
            double Lat2 = rad(latitude2);
            double a = Lat1 - Lat2;
            double b = rad(longitude1) - rad(longitude2);
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                    + Math.cos(Lat1) * Math.cos(Lat2)
                    * Math.pow(Math.sin(b / 2), 2)));
            s = s * EARTH_RADIUS;
            //有小数的情况;注意这里的10000d中的“d”
            s = Math.round(s * 10000d) / 10000d;
            s = s * 1000;//单位：米
    //        s = Math.round(s/10d) /100d   ;//单位：千米 保留两位小数
            s = Math.round(s / 100d) / 10d;//单位：千米 保留一位小数
            return s;
        }
    ```

22. 根据经纬度获取城市名

    ```java
    private String updateWithNewLocation(Location location) {
        Geocoder geocoder = new Geocoder(MainActivity.this);//能通过经纬度来获取相应的城市等信息
        String mcityName = "";
        double lat = 0;
        double lng = 0;
        List<Address> addList = null;
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
        } else {

            System.out.println("无法获取地理信息");
        }

        try {

            addList = geocoder.getFromLocation(lat, lng, 1);    //解析经纬度

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (addList != null && addList.size() > 0) {
            for (int i = 0; i < addList.size(); i++) {
                Address add = addList.get(i);
                mcityName += add.getLocality();
                Log.d("MainActivity", "add:" + add);
            }
        }
        if (mcityName.length() != 0) {

            return mcityName.substring(0, (mcityName.length() - 1));
        } else {
            return mcityName;
        }
    }

    ```

23. 创建文件及读写相关的流程方法

    ```java
    // 创建文件夹
    private void createDir() {
        mulu = new File(this.getFilesDir(), "target");
    	// 不做这个判断,文件夹可能不会创建
        if (!mulu.exists()) {
          // 创建文件夹,也就是文件目录,要与下面创建文件区别开,创建的时候要对应
            mulu.mkdirs();
        }
    }
    // 创建文件 f1, f2
    private void initF1F2() {
            f1 = new File(mulu, "f1.txt");
            f2 = new File(mulu, "f2.txt");
            try {
              // 不做这里的判断,文件可能不会创建
                if (!f1.exists()) {
                  // 创建文件,与上面创建文件夹区分开,写的时候注意
                    f1.createNewFile();
                }
                if (!f2.exists()) {
                    f2.createNewFile();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("mainactivity", "异常e=" + e);
            }
        }
    // 把数据写入 f1 中
     private void myWriteF1() {
            FileWriter fw = null;
            try {
                if (!f1.exists()) {
                    f1.createNewFile();
                }
                fw = new FileWriter(f1);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(s1);
              	// 注意:写完之后要记得关闭,不然数据可能写不到 f1 中
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("mainactivity", "异常e=" + e);
            }
        }

    // 把 f1 中的内容复制到 f2 中,本质上还是流的读写
    private void copy() {
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f1));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f2));
                byte[] arr = new byte[1024];
                int c = 0;
                while ((c = bis.read(arr)) != -1) {
                    bos.write(arr, 0, c);
                }
                bis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("mainactivity", "异常e=" + e);
            }
        }

    ```

24. 属性动画相关的操作

    ```java
    private void startAnimator() {
    
            as = new AnimatorSet();
      		// 在 xml 文件中配置动画的相关的属性
            // 透明度
            ObjectAnimator ob1 = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_alpha);
            // 旋转
            ObjectAnimator ob2x = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_rotationx);
            ObjectAnimator ob2y = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_rotationy);
            // 缩放
            ObjectAnimator ob3x = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_scalex);
            ObjectAnimator ob3y = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_scaley);
            // 平移
            ObjectAnimator ob4 = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_translationx);
            // 颜色变化
            ObjectAnimator ob5 = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.home_color);
    
      // 把动画添加到目标组件中
            ob1.setTarget(mTextView);
            ob2x.setTarget(mTextView);
            ob2y.setTarget(mTextView);
            ob3x.setTarget(mTextView);
            ob3y.setTarget(mTextView);
            ob4.setTarget(mTextView);
            ob5.setTarget(mTextView);
    // 把以上的动画添加到一起,同时生效
            as.playTogether(ob1, ob2x, ob2y, ob3x, ob3y, ob4, ob5);
      // 开始动画
            as.start();
        }
    ```

    ​	

    ​	xml文件 

    ​

    ```xml
    <!--透明度-->
    <objectAnimator
        xmlns:android="http://schemas.android.com/apk/res/android"
    
        android:duration="300"
    
        android:propertyName="alpha"
    
        android:repeatCount="100"
        android:repeatMode="reverse"
    
        android:valueFrom="0.1f"
        android:valueTo="1.0f"
        android:valueType="floatType"
    
        >
    ```


    </objectAnimator>
    ```
    
    ​	
    
    ```xml
    <!--字体颜色-->
    <objectAnimator
        xmlns:android="http://schemas.android.com/apk/res/android"
    
        android:duration="300"
    
        android:propertyName="textColor"
    
        android:repeatCount="0"
        android:repeatMode="reverse"
    
        android:valueFrom="#ffff00"
        android:valueTo="#ffffff"
        android:valueType="floatType"
    
    >


    </objectAnimator>
    ```
    
    ​	
    
    ```xml
    <!--旋转-->
    <objectAnimator
        xmlns:android="http://schemas.android.com/apk/res/android"
    
        android:duration="300"
    
        android:propertyName="rotationX"
    
        android:repeatCount="100"
        android:repeatMode="reverse"
    
        android:valueFrom="0.0f"
        android:valueTo="360.0f"
        android:valueType="floatType"
    
    >
    
    ```
    
    ​	
    
    ```xml
    <objectAnimator
        xmlns:android="http://schemas.android.com/apk/res/android"
    
        android:duration="300"
    
        android:propertyName="rotationY"
    
        android:repeatCount="100"
        android:repeatMode="reverse"
    
        android:valueFrom="0.0f"
        android:valueTo="360.0f"
        android:valueType="floatType"
    
    >


    </objectAnimator>
    
    ```
    
    ​	
    
    ```xml
    <!--缩放-->
    <objectAnimator
        xmlns:android="http://schemas.android.com/apk/res/android"
    
        android:duration="300"
    
        android:propertyName="scaleX"
    
        android:repeatCount="0"
        android:repeatMode="reverse"
    
        android:valueFrom="0.1"
        android:valueTo="1.0"
        android:valueType="floatType"
    
    >


    </objectAnimator>
    
    ```
    
    ​	
    
    ```xml
    <!--平移-->
    <objectAnimator
        xmlns:android="http://schemas.android.com/apk/res/android"
    
        android:duration="300"
    
        android:propertyName="translationX"
    
        android:repeatCount="100"
        android:repeatMode="reverse"
    
        android:valueFrom="-70"
        android:valueTo="60"
        android:valueType="floatType"
    
    >


    </objectAnimator>
    ```
    
    ​	
    
    ```java
       private void startAnimator() {
    
            as = new AnimatorSet();
         // 平移
            ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mTextView,"scaleX",0.1f,1.2f,1.2f);
            scaleXAnim.setDuration(1500);
         // 缩放   
         ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mTextView,"scaleY",0.1f,1.2f,1.2f);
            scaleYAnim.setDuration(1500);
            int colorA = Color.parseColor("#ffffff");
            int colorC = Color.parseColor("#ffff00");
         // 颜色   
         ObjectAnimator colorAnim = ObjectAnimator.ofInt(mTextView, "textColor", colorA, colorC);
            colorAnim.setDuration(1500);
            as.playTogether(
                    scaleXAnim,
                    scaleYAnim,
                    colorAnim
            );
    
            as.start();
    
         // 对动画的监听
            colorAnim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
    
                }
    
                @Override
                public void onAnimationEnd(Animator animation) {
                    Toast.makeText(MainActivity.this, "动画已开始", Toast.LENGTH_SHORT).show();
                    mTextView.setVisibility(View.GONE);
                }
    
                @Override
                public void onAnimationCancel(Animator animation) {
    
                }
    
                @Override
                public void onAnimationRepeat(Animator animation) {
    
                }
            });
    
        }
    ```
    
    ​
25. WebView 无法加载证书错误

    sslerror = primary error: 3 certificate: Issued to

    解决方法：调用SslErrorHandler 中的proceed()方法

    ```java
    @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                SslError error) {
            ToastInfo(error.toString());//ssl错误信息
            handler.proceed();// 进行ssl的认证
            LogUtil.LogE("MyWebViewClient", "sslerror = "+ error.toString());
            super.onReceivedSslError(view, handler, error);
        }
    ```

26. PopupWindow 显示在控件的不同位置

    ```java
     int[] location = new int[2];
            v.getLocationOnScreen(location);
            mChatPopView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int measuredWidth = mChatPopView.getMeasuredWidth();
            int measuredHeight = mChatPopView.getMeasuredHeight();
            //显示在上方
            chatPop.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth() / 2, location[1] - measuredHeight);
            //显示在正上方
    //                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - measuredWidth / 2, location[1] - measuredHeight);
                    //显示在左方
    //                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] - popupWindow.getWidth(), location[1]);
                    //显示在下方
    //                popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth(), location[1]);
    //                popupWindow.setAnimationStyle(android.R.style.Animation_Translucent);//设置动画
    ```

27. 保存图片后强制通知图库刷新

    ```java
    MediaScannerConnection.scanFile(ResultActiivty.this,
                        new String[]{file.getAbsolutePath()},
                        new String[]{"image/jpeg"},
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Log.i("result ","onScanCompleted"+path);
                            }
                        });
                        
                        
    // 这种方法通知图库更新,有时候对于个别手机会有延时现象
    
         MediaStore.Images.Media.insertImage(getContentResolver(),
                file.getAbsolutePath(), fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/"); Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
    ```

28. base 64 字符串转成 bitmap

    ```java
    public Bitmap stringtoBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    ```

29. 把 bitmap 保存到手机文件中

    ```java
    public static void saveImage(Context context,
            String path
            , String name
            , Bitmap bm
            , int quality) throws IOException {
    
        if (bm == null
                || TextUtils.isEmpty(path)
                || TextUtils.isEmpty(name)
                )
            return;
    
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(path, name);
    
        FileOutputStream fos = new FileOutputStream(file);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        byte[] bytes = stream.toByteArray();
        fos.write(bytes);
        fos.close();
        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                file.getAbsolutePath(), name, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/");
    
        intent.setData(uri);
        context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
        ToastUtil.showToast(context,"保存成功");
    ```

30. 模拟屏幕点击事件

    ```java
    // //模拟触屏点击屏幕事件
    private void emulatorTouch() {
        int x = 500; // 屏幕坐标
        int y = 30;
        final long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(
                downTime, downTime, MotionEvent.ACTION_DOWN, x, y, 0);
        final MotionEvent upEvent = MotionEvent.obtain(
                downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0);
        //添加到webview_loading_round_iv上
        mWebview.onTouchEvent(downEvent);
        mWebview.onTouchEvent(upEvent);
    
    
        downEvent.recycle();
        upEvent.recycle();
    }
    ```

31. 网络图片转成 base64

    ```java
    public static String GetUrlImageToBase64(String url) throws Exception {
        if (url == null || "".equals(url.trim()))
            return null;
    
        InputStream inputStream = null;
        URL imgurl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) imgurl.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(20000);
        if (conn.getResponseCode() == 200) {
            inputStream = conn.getInputStream();
        }
        byte[] buffer = new byte[2048];
        inputStream.read(buffer);
        inputStream.close();
        Log.e("LocalInvoiceInputActivi", "base 64 :" + (Base64.encodeToString(buffer, Base64.DEFAULT)));
    
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }
    ```

32. 动态设置 textview 的drawable

    ```java
    Drawable drawable = getResources().getDrawable(R.drawable.close_red);
    drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
    mResultInfoTv.setCompoundDrawables(drawable,null,null,null);
    ```

33. 数字在字符串的位置

    ```java
    int index = 0;
    for (int i = 0; i < value.length(); i++) {
        if (value.charAt(i) >= 48 && value.charAt(i) <= 57) {
            index = i;
            break;
        }
    }
    ```

34. 获取 SHA1

    ```java
    进入终端 
    
    cd .android 
    
    开发版： 
    
    keytool -list -v -keystore debug.keystore 
    
    不输入密令直接回车 
    
    发布版： 
    
    keytool -list -v -keystore 签名时使用的 .jks 文件的路径 
    
    回车 
    
    输入签名时的密令回车 
    ```

35. 导入多个 jar 包是报资源重复（duplicate）

    ```java
    // app build.gradle 文件中
    android
    
    {
        // exclude 后面加上报错文件
        packagingOptions {
        exclude 'META-INF/DEPENDENCIES'
        exclude 'META-INF/NOTICE'
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE.txt'
        exclude 'META-INF/NOTICE.txt'
        exclude 'assets/main_icon_zoomout.png'
        exclude 'assets/logo_h.png'
        exclude 'assets/main_topbtn_down.png'
        exclude 'assets/*.png'
    }
    }
    ```

36. 百度地图导航时引擎初始化失败

    ```
    官方的 demo 里，在 main 文件夹下有一个 assets 文件夹，里面有一个 .png 文件，把它复制到项目相应的文件夹下。
    
    这个 .png 其实不是个 png，而一个 .zip，里面是一些资源文件，如果没有这些资源文件就会出错，但是错误又不明显，看起来都是一些不太相关的错误，而且在官方文档里也并没有说这个问题，搞什么，百度真特么是一大坑，大坑，大坑！！！！！！！！！！！
    ```

37. 当弹出键盘时页面中的内容跟着向上移动

    ```java
     在注册 activity 时添加该属性 android:windowSoftInputMode="adjustResize"
        在布局文件中把要移动的部分用 ScrollView 包起来
    ```

38. 页面中有 edittext 时，打开时不弹出键盘

    ```java
     在注册 activity 时添加属性
        android:windowSoftInputMode="adjustUnspecified|stateHidden"
    ```

39. 状态栏和导航栏的设置

    ```java
    // 沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.0以上使用原生方法
    //            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
    //                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
    //            );
    
                // 当 setStatusBarColor 设置为 TRANSPARENT（透明）时，
                // window.getDecorView().setSystemUiVisibility()
                // 设置 View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN （全屏）时，statusBar 会遮挡布局
                // 如果setStatusBarColor 设置为其他颜色时，设置全屏属性时也不会遮挡布局
    //            window.setStatusBarColor(Color.TRANSPARENT);
    //            window.setStatusBarColor(Color.GRAY);// 顶部状态栏的颜色
    
    //            window.setNavigationBarColor(Color.BLUE);
    //            window.setNavigationBarColor(Color.BLUE); // 底部状态栏的颜色，部分手机会有
                window.addFlags(
    //                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
    //                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION // 底部导航栏透明，设置该属性之后底部导航栏设置什么颜色都不起做用
    //                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS // 顶端状态栏透明，设置该属性后，导航栏设置其他的颜色都不起作用
    //                    WindowManager.LayoutParams.FLAG_FULLSCREEN // 全屏，不显示顶部状态栏，显示下面导航栏
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
     
                );
    
                
                window.getDecorView().setSystemUiVisibility(
                        // 导航栏上的图标会变暗，有的不可见
    //                    View.SYSTEM_UI_FLAG_LOW_PROFILE |
                        // 全屏 上面导航栏不显示
    //                    View.SYSTEM_UI_FLAG_FULLSCREEN
                        // 上面会遮挡，下面不遮挡
    //                  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    
                        // 与 View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 一起使用,文档中说如果不设置该值，
                        // 系统使用其他值时会清除掉View.SYSTEM_UI_FLAG_HIDE_NAVIGATION这个值，但没看到效果
    //                   View.SYSTEM_UI_FLAG_IMMERSIVE
    
                        // 与 View.SYSTEM_UI_FLAG_FULLSCREEN 一起使用时，上面的状态栏不显示，从屏幕顶部向下滑动时
                        // 状态栏会出现，一段时间后消息
                        // 与 SYSTEM_UI_FLAG_HIDE_NAVIGATION 一起使用时类似
                        // 在真机上部分会出现状态栏白色的情况
    //                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    
                        // 布局会扩展到全屏，上下导航栏会遮挡布局
    //                       View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //自动隐藏下边导航栏，手动调出时，不会遮挡布
                        // View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    
                        // 不全屏，布局在状态栏下面
                         View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    
                        // 状态栏文字显示深色
    //                     View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    
                );
    
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
    //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //            SystemBarTintManager tintManager = new SystemBarTintManager(this);
    //            tintManager.setStatusBarTintEnabled(true);
    //            tintManager.setStatusBarTintColor(Color.TRANSPARENT);
            }
    ```

40. BottomNavigationView 一些相关设置

    ```xml
     <BottomNavigationView
            android:id="@+id/bottom_navigation"
            android:layout_width="match_parent"
            android:layout_height="56dp"
            android:layout_alignParentBottom="true"
            app:itemBackground="?android:attr/windowBackground"
            // 里面图标的布局，以 mene 的形式
            app:menu="@menu/main_bottom_navigation"
            // 设置图标的颜色，如果不设置的话，默认取 colorPrimary 的颜色值，只是改变图标的颜色，不会改变图标
            app:itemIconTint="@drawable/navi_select_color"
            // 设置文字的颜色，与图标一样
            app:itemTextColor="@drawable/navi_select_color"
            tools:layout_editor_absoluteX="8dp"
            tools:layout_editor_absoluteY="0dp" />
            
    ```

    navi_select_color

    ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:state_checked="true"
        android:color="@color/colorPrimaryNew"/>
        // 这里是 color
        <item android:state_checked="false" android:color="@color/colorPrimaryNormal"/>
    
        </selector>
    ```

    在 bottomNavigationView 中的设置的图标不会使用 selector 去改变选中时和非选中时的图标，因为它是用 menu 来实现的。 真不知道为什么要用这个来做导航栏。



    修改选中图标的方法为

    ```java
    1. bottom_navigation.setItemIconTintList(null);
    2.
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();// 设置不选中的默认图标
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //在这里替换图标
                        item.setIcon(R.mipmap.ic_home_selected);
                        return true;
                    ...
                }
                return false;
            }
            };
    
    
    private void resetToDefaultIcon() {
            MenuItem mine = bottom_navigation.getMenu().findItem(R.id.navigation_me);
            mine.setIcon(R.mipmap.mine_normal);
    
        }
    ```

41. AppbarLayout 里面的 Toolbar 去掉阴隐

    ```xml
    <android.support.design.widget.AppBarLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay"
        // 是 app ，不是 android
        app:elevation="0dp"
        >
    ```
