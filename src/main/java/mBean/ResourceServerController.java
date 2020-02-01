package mBean;

import resources.TestResource;

public class ResourceServerController implements ResourceServerControllerMBean{
  private TestResource testResource;

  public ResourceServerController(TestResource testResource){
    this.testResource = testResource;
  }

  @Override
  public String getName(){
    return testResource.getName();
  }

  @Override
  public int getAge(){
    return testResource.getAge();
  }

  @Override
  public void setTestResource(TestResource testResource){
    this.testResource = testResource;
  }
}
