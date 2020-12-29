package com.xkdjzt.web.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan("com.xkdjzt.web.dao")
@PropertySource("classpath:mysql.properties")
public class MyBatisConfig {

	@Value("${mysql.url}")
	private String MYSQL_URL;
	@Value("${mysql.username}")
	private String MYSQL_USERNAME;
	@Value("${mysql.password}")
	private String MYSQL_PASSWORD;
	@Value("${mysql.driverClassName}")
	private String MYSQL_DRIVERCLASSNAME;
	
	
	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(MYSQL_URL);
		dataSource.setUsername(MYSQL_USERNAME);
		dataSource.setPassword(MYSQL_PASSWORD);
		dataSource.setDriverClassName(MYSQL_DRIVERCLASSNAME);
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:com/xkdjzt/web/mapper/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.xkdjzt.web.entity");
		Properties properties = new Properties();
		properties.setProperty("cacheEnabled", "true");
		sqlSessionFactoryBean.setConfigurationProperties(properties);
		return sqlSessionFactoryBean;
	}
}
