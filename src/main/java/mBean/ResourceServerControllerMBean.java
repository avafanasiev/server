package mBean;

import resources.TestResource;

public interface ResourceServerControllerMBean {
  String getName();

  int getAge();

  void setTestResource(TestResource testResource);
}
