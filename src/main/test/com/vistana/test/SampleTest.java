package com.vistana.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.vistana.domain.SecurityQuestionAnswer;
import com.vistana.domain.User;
import com.vistana.enumeration.SecurityQuestion;
import com.vistana.session.ApplicationSession;

import junit.framework.TestCase;


public class SampleTest extends TestCase {

	private ApplicationSession session;
	
    protected void setUp() { 
         session = new ApplicationSession();
         User user = new User();
         user.setDob(new Date());
         user.setUsername("testuser");
         
         SecurityQuestionAnswer sqa1 = new SecurityQuestionAnswer();
         sqa1.setSecurityQuestion(SecurityQuestion.FIRST_JOB);
         sqa1.setAnswer("new your city");
         SecurityQuestionAnswer sqa2 = new SecurityQuestionAnswer();
         sqa2.setSecurityQuestion(SecurityQuestion.HOUSE_NUMBER);
         sqa2.setAnswer("7125");
         SecurityQuestionAnswer sqa3 = new SecurityQuestionAnswer();
         sqa3.setSecurityQuestion(SecurityQuestion.PHONE_NUMBER);
         sqa3.setAnswer("0358");
         
         List<SecurityQuestionAnswer> sqaList = new ArrayList<SecurityQuestionAnswer>();
         sqaList.add(sqa1);
         sqaList.add(sqa2);
         sqaList.add(sqa3);
         user.setSecurityQuestionAnswers(sqaList);
         
         session.setUser(user);
    }
    

    protected void tearDown() { 
         session = null;
    } 
    
    @Test
    public void testForValidUser() {
       org.junit.Assert.assertTrue( session.getUser().isValid() );
    }


}
