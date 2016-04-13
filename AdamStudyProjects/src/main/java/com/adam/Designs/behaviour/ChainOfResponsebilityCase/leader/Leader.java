package com.adam.Designs.behaviour.ChainOfResponsebilityCase.leader;
/**
 * 责任链模式作业
 * 我们试着模拟这样一个责任链
 * 公司员工小张，手里需要声请一笔聚餐费用，共计1000元，然后向他的直接上级项目经理声请，由于项目经理只能批准小于等于300元的费用，所以他自然就需要向他的上级--部门经理
 * 反映，而部门经理只能批准小于等于800元的费用，他也没办法，只好向总经理进行反映了，然而总经理可以批准任何大小的费用，所以最后通过总经理，就成功批注了小张的请求
 * 在这整个过程中，项目经理，部门经理，总经理就形成了一个领导层的责任链，由项目经理接收请求，由于自己不能满足请求，就把请求传递给下一个领导，部门经理处理，这样一环一环的向下
 * 传递，总会有一个能够处理请求的领导，而小张本身，并不关心谁处理的，他只需要得到一个结果，声请成功或者失败就好了。
 * @author adam
 * 2015.5.19
 */
public interface Leader {

	//这就是我们的领导接口，定义一个处理声请的方法
	public void dealApply(String emp, Long price);
	
	public void setUpLeader(Leader l);
	
	public Leader getUpLeader();
	
}
