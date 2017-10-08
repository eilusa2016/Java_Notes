package org.svse.service.impl;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.svse.dao.StuDAO;
import org.svse.entity.StuInfo;
import org.svse.service.StuInfoService;

@Transactional
public class StuInfoServiceImpl implements StuInfoService {

	private StuDAO studao ;
	
//	private TransactionTemplate transactionTemplate;
//	
//	
//	public void setTransaction(PlatformTransactionManager transactionManager){
//		
//		transactionTemplate = new TransactionTemplate(transactionManager);
//		
//	}
//	
	
	public void setStudao(StuDAO studao) {
		this.studao = studao;
	}




	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public void saveStuInfo(final StuInfo stu) throws Exception {
		this.studao.addStu(stu);
		
		try {
			throw new Exception("����쳣");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
//		this.transactionTemplate.execute(new TransactionCallbackWithoutResult(){
//
//			@Override
//			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
//				// ���ݲ�����������
//				studao.addStu(stu);
//				System.out.println("ѧ����ӳɹ���");
			
//			}
//			
//		});
	}

}
