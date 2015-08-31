package com.zhongduan.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class SatelliteService  {
	private SatelliteDataBseHelper databaseHelper;  //SatelliteDataBseHelper��SQLiteOpenHelper�ĳ�����
	private Context context;                        //������
	private 	SQLiteDatabase db;  
	private Satellite  satellite;
	private final String TAG="SatelliteService";
	/**
	 * 
	 * @param ���ǵĲ���
	 * private Integer  satellite_Id;
	private String satellite_Name;  //��������
	private String satellite_Longitude;//���Ǿ���
	private String satellite_Polarization;//������ʽ
	private String satellite_Beacon;//�ű�Ƶ��
	private String satellite_Carrier;//�ز�
	private String satellite_Sign;//����Ƶ��
	 */
	
	
	public SatelliteService(Context  context) {  //���췽�� ����������
		// TODO Auto-generated constructor stub
		this.context=context;
		databaseHelper=new SatelliteDataBseHelper(context);
		
	}
	
	
	
	public void  insert(Satellite satelite){             //��������
               db=	databaseHelper.getWritableDatabase();//�õ�һ����д�����ݿ�Ķ���
              Log.i(TAG, "insert");
               db.execSQL("insert into satelliteParameter_table" +
               		" (" +
               		"satellite_Name," +
               		"satellite_Longitude," +
               		"satellite_Polarization," +
               		"satellite_Beacon," +
               		"satellite_Carrier," +
               		"satellite_Sign)" +
               		" values (?,?,?,?,?,?)", 
               		
               		new Object [ ]{
            		   satelite.getSatellite_Name(),
                       satelite.getSatellite_Longitude(),
                       satelite.getSatellite_Polarization(),
                       satelite.getSatellite_Beacon(),
                       satelite.getSatellite_Carrier(),
                       satelite.getSatellite_Sign() });
               Log.i(TAG, "insert");
	} 
//	15829880756
	
	public void deleteId (Integer  id){                       //����satellite_Idɾ��Satellite
		db=	databaseHelper.getWritableDatabase();//�õ�һ����д�����ݿ�Ķ���
//		db.execSQL("delete from  satelliteParameter_table  where   satellite_Id=?",new Object[]{id} );
		db.execSQL("delete from satelliteParameter_table where satellite_Id=?", new Object[] { id });
	}
	
	public void deleteName  (String name){           //����satellite_Nameɾ��Satellite
		db=databaseHelper.getWritableDatabase();
		db.execSQL("delete from satelliteParameter_table where  satellite_Name=?",new String[]{ name});	
		    }
	
	
	
	@SuppressWarnings("finally")
	public  List<Satellite> selectAll(){                             //ģ����ѯ����Satellite
		List<Satellite>  satellites=new ArrayList<Satellite>();
		db=databaseHelper.getReadableDatabase();
	    Cursor cursor=db.rawQuery("select * from satelliteParameter_table",  null);
		try {
			while (cursor.moveToNext()) {
		    satellite=new Satellite();
			satellite.setSatellite_Id(cursor.getInt(0));
			satellite.setSatellite_Name(cursor.getString(1));
			satellite.setSatellite_Longitude(cursor.getString(2));
			satellite.setSatellite_Polarization(cursor.getString(3));
			satellite.setSatellite_Beacon(cursor.getString(4));
			satellite.setSatellite_Carrier(cursor.getString(5));
		    satellite.setSatellite_Sign(cursor.getString(6));
		    satellites.add(satellite);   //�������������
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			cursor.close();
			return satellites;
		}
	}
 
	@SuppressWarnings("finally")                            //����satellite_Id��ѯ����
	public  Satellite find (Integer  id){
		db=databaseHelper.getReadableDatabase();
		  satellite=new Satellite();
		Cursor cursor=db.rawQuery("select satellite_Id ," +
				" satellite_Name," +
				"satellite_Longitude," +
				"satellite_Polarization," +
				"satellite_Beacon," +
				"satellite_Carrier," +
				"satellite_Sign  " +
				"from satelliteParameter_table " +
				" where satellite_Id=?", 
				new String[]{String.valueOf(id)});
		 try {
			if(cursor!=null){
				if (cursor.moveToNext()) {
					satellite.setSatellite_Id(cursor.getInt(cursor.getColumnIndex("satellite_Id")));
					satellite.setSatellite_Name(cursor.getString(1));
					satellite.setSatellite_Longitude(cursor.getString(2));
					satellite.setSatellite_Polarization(cursor.getString(3));
					satellite.setSatellite_Beacon(cursor.getString(4));
					satellite.setSatellite_Carrier(cursor.getString(5));
				    satellite.setSatellite_Sign(cursor.getString(6));			
				}		
			}		 
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			cursor.close();
			db.close();
			return satellite;
		}
	}
	
	public  void  updata(Satellite satellite){    //ͨ��setSatellite_Id ����������
		db=databaseHelper.getWritableDatabase();
		db.execSQL("updata satelliteParameter_table  " +
				"set setSatellite_Name=?," +
				" set Satellite_Longitud=?," +
				"set  setSatellite_Polarization=?, " +
				"set setSatellite_Beacon=?, " +
				"set  setSatellite_Carrier=?, " +
				"set  setSatellite_Sign=? " +
				"where setSatellite_Id=?",
				new Object[]{ 
				satellite.getSatellite_Name(),
				satellite.getSatellite_Longitude(),
				satellite.getSatellite_Polarization(),
				satellite.getSatellite_Beacon(),
				satellite.getSatellite_Carrier(),
				satellite.getSatellite_Sign(),
				satellite.getSatellite_Id()});
	}
	
}
