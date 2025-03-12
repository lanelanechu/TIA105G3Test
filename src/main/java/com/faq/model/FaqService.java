package com.faq.model;

import java.util.List;

public class FaqService {

    private FaqDAO_interface dao;

    public FaqService() {
        dao = new FaqJDBCDAO();
    }

    // 新增 FAQ
    public FaqVO addFaq(String faqId, String adminId, String faqAsk, String faqAnswer, Integer faqStatus) {
        FaqVO faqVO = new FaqVO();

        faqVO.setFaqId(faqId);
        faqVO.setAdminId(adminId);
        faqVO.setFaqAsk(faqAsk);
        faqVO.setFaqAnswer(faqAnswer);
        faqVO.setFaqStatus(faqStatus);
        
        dao.insert(faqVO); 
        return faqVO;
    }

    // 修改 FAQ
    public FaqVO updateFaq(String faqId, String adminId, String faqAsk, String faqAnswer, Integer faqStatus) {
        FaqVO faqVO = new FaqVO();

        faqVO.setFaqId(faqId);
        faqVO.setAdminId(adminId);
        faqVO.setFaqAsk(faqAsk);
        faqVO.setFaqAnswer(faqAnswer);
        faqVO.setFaqStatus(faqStatus);

        dao.update(faqVO); // 執行 DAO 層的 update 方法

        return faqVO;
    }

    // 刪除 FAQ
    public void deleteFaq(String faqId) {
        dao.delete(faqId);
    }

    // 查詢單筆 FAQ
    public FaqVO getOneFaq(String faqId) {
        return dao.findByPrimaryKey(faqId);
    }

    // 查詢所有 FAQ
    public List<FaqVO> getAll() {
        return dao.getAll();
    }
}
