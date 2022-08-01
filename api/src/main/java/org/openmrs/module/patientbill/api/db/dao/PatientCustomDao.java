package org.openmrs.module.patientbill.api.db.dao;

import java.util.List;

import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.patientbill.api.db.impl.PatientCustomDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class PatientCustomDao implements PatientCustomDaoImpl {
	
	private static final Logger log = LoggerFactory.getLogger(PatientCustomDao.class);
	
	@Setter
	private DbSessionFactory sessionFactory;
	
	@Override
	@Transactional(readOnly = true)
	public List<Patient> searchPatientByName(String searchName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class, "person");
		criteria.createAlias("person.names", "personName", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("person.attributes", "PersonAttribute", JoinType.LEFT_OUTER_JOIN);
		
		Disjunction nameOr = Restrictions.disjunction();
		nameOr.add(Restrictions.ilike("personName.givenName", searchName, MatchMode.ANYWHERE));
		nameOr.add(Restrictions.ilike("personName.middleName", searchName, MatchMode.ANYWHERE));
		nameOr.add(Restrictions.ilike("personName.familyName", searchName, MatchMode.ANYWHERE));
		nameOr.add(Restrictions.ilike("personName.familyName2", searchName, MatchMode.ANYWHERE));
		
		Conjunction personNameConjuction = Restrictions.conjunction();
		personNameConjuction.add(Restrictions.eq("personName.voided", false));
		personNameConjuction.add(nameOr);
		
		Disjunction or = Restrictions.disjunction();
		or.add(personNameConjuction);
		
		criteria.add(or);
		return (List<Patient>) criteria.list();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Patient> searchPatientByMobileNo(String mobileNo) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class, "person");
		
		criteria.createAlias("person.names", "personName", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("person.attributes", "personAttribute", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("personAttribute.attributeType", "attributeType", JoinType.LEFT_OUTER_JOIN);
		
		criteria.add(Restrictions.eq("personAttribute.value", mobileNo));
		criteria.add(Restrictions.eq("attributeType.name", "Telephone Number"));
		return (List<Patient>) criteria.list();
	}
}
