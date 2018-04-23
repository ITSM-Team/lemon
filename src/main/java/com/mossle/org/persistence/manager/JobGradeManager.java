package com.mossle.org.persistence.manager;

import com.mossle.core.hibernate.HibernateEntityDao;

import com.mossle.org.persistence.domain.JobGrade;

import org.springframework.stereotype.Service;

@Service
public class JobGradeManager extends HibernateEntityDao<JobGrade> {
}
