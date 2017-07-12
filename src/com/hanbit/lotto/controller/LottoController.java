package com.hanbit.lotto.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.hanbit.lotto.domain.LottoBean;
import com.hanbit.lotto.service.LottoService;
import com.hanbit.lotto.serviceImpl.LottoServiceImpl;

public class LottoController {
	public static void main(String[] args){
		LottoService service = new LottoServiceImpl();
		
		
		while(true){
			switch(JOptionPane.showInputDialog("0.종료   1.로또 구입")){
			case "0" :
				JOptionPane.showMessageDialog(null, "종료합니다.") ;
				return;
			case "1" :
				LottoBean bean = new LottoBean();
				StringBuffer buff = new StringBuffer();
				bean.setMoney(Integer.valueOf(JOptionPane.showInputDialog("얼마치를 구입하십니까?")));
				service.setLottos(bean);
				int[][] lottos = service.getLottos();
				for(int i=0; i<lottos.length; i++){
					for(int j=0; j<lottos[i].length; j++){
						//System.out.print(lottos[i][j]+"\t");
						buff.append(lottos[i][j]+"\t");
					}
					buff.append("/");
				}
				int lottoSerialNo = (int)(Math.random()*99999+10000);
				File output = new File("C:\\Users\\1027\\JavaProject\\lottos\\"+lottoSerialNo+".txt");
				FileWriter fw=null;
				BufferedWriter bw = null;
				String[] lottoPrint = buff.toString().split("/");
				try {
					bw = new BufferedWriter(new FileWriter(output));
					for(String s:lottoPrint){
						s+=System.getProperty("line.separator");
						bw.write(s);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						bw.flush();
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
		}
	}

}
