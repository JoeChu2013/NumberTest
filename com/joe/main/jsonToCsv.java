package com.joe.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.joe.json.MapBean;

public class jsonToCsv {

	public static void main(String[] args) throws IOException {
		 String path = "/Users/chuqiao/Desktop/program/workspace/NumberTest/src/leveldata";
		 ArrayList<File> fileList = getJsonFiles(path);
		 List<MapBean> mapList = setMapBean(fileList);
		 Object[] head = {"levelId","cell","component","fish","move","predefine","score","target","targetNum"};
		 List<Object> headList = Arrays.asList(head);
		 List<List<Object>> dataList = new ArrayList<List<Object>>();
		 List<Object> list = null;
		 for(int i=0;i<mapList.size();i++) {
			 list = new ArrayList<Object>();
			 list.add(mapList.get(i).getLevelid());
			 list.add(mapList.get(i).getCell());
			 list.add(mapList.get(i).getComponent());
			 list.add(mapList.get(i).getFish());
			 list.add(mapList.get(i).getMove());
			 list.add(mapList.get(i).isPredefine());
			 list.add(mapList.get(i).getScore());
			 list.add(mapList.get(i).getTarget());
			 list.add(mapList.get(i).getTargetNum());
			 dataList.add(list);
		 }
		 String downloadFilePath = "/Users/chuqiao/Desktop/program/workspace/NumberTest/"+ File.separator;
		 String fileName = "level";
		 CSVUtils.createCSVFile(headList, dataList, downloadFilePath, fileName);
		 
	}
	public static ArrayList<File>  getJsonFiles(String path) {
		ArrayList<File> fileList = new ArrayList<File>();
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return fileList;
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");
            } else {
               fileList.add(fs);
            }
        }
        return fileList;
    }
	
	public static ArrayList<MapBean> setMapBean(ArrayList<File> fileList) {
		ArrayList<MapBean> mapList = new ArrayList<MapBean>();
		for (int i = 0; i < fileList.size(); i++) {
			InputStreamReader inputStreamReader;
			try {
				inputStreamReader = new InputStreamReader(new FileInputStream(fileList.get(i)));
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String lineTxt = null;
				StringBuilder stringBuilder = new StringBuilder();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					stringBuilder.append(lineTxt);
				}
				bufferedReader.close();
				inputStreamReader.close();
				// System.out.println(stringBuilder.toString());
				MapBean map = JSON.parseObject(stringBuilder.toString(), MapBean.class);
				map.setLevelid(fileList.get(i).getName());
				mapList.add(map);
				// System.out.println(map.getLevelid());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mapList;
	}
}
