package com.hanbit.lotto.serviceImpl;

import java.util.ArrayList;
import java.util.Random;

import com.hanbit.lotto.domain.LottoBean;
import com.hanbit.lotto.service.LottoService;

public class LottoServiceImpl implements LottoService{
	int[][] lottos;
	int[] lotto; //돈과 상관없이 생성되는 한 줄(숫자6) 로또
	private int count;
	LottoBean bean;
	public LottoServiceImpl() {
		count = 0;
		bean = new LottoBean();
	}


	@Override
	public void setLottos(LottoBean bean) {
		setCount(bean);
		lottos = new int[count][6];
		/*int i=0;
		for(int j=0; j<lottos.length; j++){
			while(true){
				int num = bean.getNumber();
				if(isDuplication(j, num)){
					continue;
				}
				lottos[j][i]=num;
				i++;
				if(i==lottos[j].length){
					sort(lottos[j]);
					i=0;
					break;
				}
			}
		}*/
		for(int i=0; i<lottos.length; i++){
			for(int j=0; j<lottos[i].length; j++){
				int num=bean.getNumber();
				if(!isDuplication(i, num)){
					lottos[i][j]=num;
				}
				else
					j--;
			}
			sort(lottos[i]);
		}
		
	}

	@Override
	public int[][] getLottos() {
		//만든 로또 가져오기
		return lottos;
	}

	@Override
	public boolean isDuplication(int count, int num) {
		//중복된 번호인지 체크(중복이면 true 리턴)
		boolean result = false;
		for(int i=0; i<lottos[count].length; i++){
			if(lottos[count][i]==num){
				result = true;
				break;
			}
		}
		
		return result;
		
	}

	@Override
	public void sort(int[] arr) {
		// 오름차순으로 정렬
		for(int i=0; i<arr.length-1; i++){
			for(int j=0; j<arr.length-i-1; j++){
				if(arr[j]>arr[j+1]){
					int x=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=x;
				}
			}
		}
	}

	@Override
	public int getCount() {
		// 해당 로또수만큼 출력
		return count;
	}

	@Override
	public void setCount(LottoBean bean) {
		// 몇 줄을 출력할 것인지 로또 수 계산(최대값 5줄)
		/*int a = bean.getMoney()/1000;
		if(a>5)
			a = 5;
		else
			a = bean.getMoney()/1000;*/
		//삼항 연산자! 
		this.count=(bean.getMoney()/1000>5)?5:bean.getMoney()/1000;
	}
}
