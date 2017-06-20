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

3. 遍历 set 的方法 
  
		1.迭代遍历：  
		Set<String> set = new HashSet<String>();  
		Iterator<String> it = set.iterator();  
		 while (it.hasNext()) {  
 		 String str = it.next();  
  		System.out.println(str);  
		}  
  
		2.for循环遍历：  
		for (String str : set) {  
      	System.out.println(str);  
		}  
  
  
		3.当 set 中存放的是 object 的时候用这种方法
		Set<Object> set = new HashSet<Object>();  
		for循环遍历：  
		for (Object obj: set) {  
      		if(obj instanceof Integer){  
                int aa= (Integer)obj;  
             }else if(obj instanceof String){  
               String aa = (String)obj  
             }  
              ........  
		}   

4. 	根据经纬度获取两点之间的距离

	 	public static double getDistance(double lat1, double lon1, double lat2,
                                     double lon2) {
        float[] results = new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0];
    }

5. 判断是否是手机号码
   
    	public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
        }
        
6. 验证用户名
   
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

    	public static boolean isUserNameValid2(String userName) {
        boolean isValid = false;
        String expression2 = "^[1-9]\\d*|0$";
        CharSequence inpustr = userName;
        Pattern pattern = Pattern.compile(expression2);
        Matcher matcher = pattern.matcher(inpustr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
7. 验证密码
       
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
    			

    
8. 正则表达式判断邮箱格式是否正确

        public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    	}
    	
    	
    	public static boolean isUrl(String str){
        boolean isValid = false;
        String expression = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        CharSequence inputStr = str;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
   		}



   
  9. 设置hint大小
  
    	public static SpannableString setHintSize(String hint, int size) {
        SpannableString ss = new SpannableString(hint);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString spannableString = new SpannableString(ss);
        return spannableString;
    	}
  		

    
 10. 将时间戳转为代表"距现在多久之前"的字符串
 
 	
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
            sb.append(day-1 + "天");
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

    	public static String getBetweenTime(String nowTime,String afterTime) {

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
                }else {
                    buffer.append(days+"天前");
                }

            }else if (days == 0 && hours != 0) {
                buffer.append(hours+"小时前");
            }else if (days == 0 && hours == 0 && minutes != 0) {
                if (minutes < 3) {
                    buffer.append("刚刚");
                }else {
                    buffer.append(minutes+"秒前");
                }

            }
            return buffer.toString();
        } catch (Exception e) {
            return afterTime;
        }

    	}

 
 	
    
 11. 验证身份证号
    
    	public static boolean isIDCardNumberValid(String id_card) {
       	boolean isValid = false;
        String expression = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        CharSequence inputStr = id_card;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    	}

    	public static String getJson(Context context, int path) {
        String json = "";
        InputStream is = context.getResources().openRawResource(
                path);
        byte[] buffer;
        try {
            buffer = new byte[is.available()];
            is.read(buffer);// 将字节数组转换为以GB2312编码的字符串
            json = new String(buffer, "GB2312");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
   	 	}

   
12. 比较两个时间大小
    
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
        long day = mydate.getTime() - date.getTime() ;
        if (day >= 0)
            return 1;
        else return 0;
    	}