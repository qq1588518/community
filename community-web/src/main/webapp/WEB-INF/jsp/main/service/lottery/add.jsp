<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../../../common/include_core.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title><spring:message code="project.name"/></title>
    <%@include file="../../../../common/include_web_meta.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/css/main/index.css">
    <link rel="stylesheet" type="text/css" href="${path}/css/main/flowdetail.css">
    <%@include file="../../../../common/include_common_script.jsp"%>
    <script type="text/javascript" src="${path}/js/main/lottery/add.js"></script>
  </head>

<body>
	<div class="content">
		<div class="top">
			<div class="top1">
				<div class="top1_t">
					<div class="top1_tleft fl">
						<p class="logo3">
							<a href="${path}/manage/logout"><spring:message code="project.name"/></a>
						</p>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/flow/list">我的工作</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/notice/listNotice">公告</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/activities/listActivities">活动</a></li>
						</ul>
					</div>
					<div class="top1_cont fl">
						<ul>
							<li><a href="${path}/service/lottery/list" class="hover">活动抽奖</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--cont start-->
		<div class="cont1">
			<div class="xg_zhsz">
				<form action="${path}/service/lottery/save" id="lottery_form" name="lottery_form" method="post">
				<div class="zxxg_zhsz fl">
					<p class="xg_zhsz_bt">&nbsp;&nbsp;基本资料&nbsp;&nbsp;</p>
					<div class="zxxg_zhsz1">
						<table id="lottery_term" width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr align="center" valign="middle">
								<td width="102" height="43" valign="middle" align="right">活动名称： </td>
								<td width="310" valign="middle" colspan="2" align="left">
									<label>
										<input id="name" class="zhsz_1_k" type="text" name="name" maxlength="15" placeholder="奖项名称">
										<label style="color: red;">*</label>
									</label>
								</td>
								<td height="43" width="102" valign="middle" align="right"> 参与频率： </td>
								<td width="310" colspan="2" valign="middle" align="left">
									<label>
										<select class="zhsz_1_k" id="rateType" name="rate">
											<c:forEach var="val" begin="1" end="5" step="1">
												<option value="${val}">
													<n:lotteryrate key="${val}"/>
												</option>
											</c:forEach>
										</select>
									</label>
								</td>
							</tr>
							<tr align="center" valign="middle">
								<td width="102" height="43" valign="middle" align="right">开始时间： </td>
								<td width="310" valign="middle" colspan="2" align="left">
									<label>
										<input type="text" class="zhsz_1_k" readonly="readonly" placeholder='<n:dft pattern="yyyy-MM-dd" value="${now}"/>' style="padding-left:5px;" id="startDate" name="startDate" value="" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1})}'});">
										<label style="color: red;">*</label>
									</label>
								</td>
								<td height="43" width="102" valign="middle" align="right">结束时间： </td>
								<td width="310" colspan="2" valign="middle" align="left">
									<label>
										<input type="text" class="zhsz_1_k" readonly="readonly" placeholder='<n:dft pattern="yyyy-MM-dd" value="${now}"/>' style="padding-left:5px;" id="endDate" name="endDate" value="" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:1})}'});">
										<label style="color: red;">*</label>
									</label>
								</td>
							</tr>
							<tr align="center" valign="middle">
								<td width="102" height="43" valign="middle" align="right">概率基数： </td>
								<td width="310" valign="middle" colspan="2" align="left">
									<label><span style="font-family:Courier New;font-size:20px;">1/</span>
										<input type="text" class="khgl_xl valid" id="radix" name="radix" maxlength="9" placeholder="100000" value="">
										<label style="color: red;">*</label>
									</label>
								</td>
								<td colspan="3">&nbsp;</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="zxxg_zhsz fl">
					<p class="xg_zhsz_bt" style="margin-top: 0px;">
						&nbsp;&nbsp;奖品设置&nbsp;&nbsp;
						<a style="cursor:pointer;" name="add" title="添加奖品项">
							<img width="20" height="20" src="${path}/images/main/add.png"/>
						</a>
					</p>
					<div class="zxxg_zhsz1">
						<table id="prizes_term" width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="35px;" align="center" valign="middle" style="border-right: 1px dashed #cbcbcb;background:#f2f2f2;">
								<td valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">奖项名称</td>
								<td width="200" valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">中奖概率</td>
								<td width="60" 	valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">是否奖品</td>
								<td width="60" 	valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">排序</td>
								<td width="100" valign="middle" align="center" style="border-right: 1px dashed #cbcbcb;">操作</td>
							</tr>
							<tr height="35px;" align="center" valign="middle" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">
									<input type="text" maxlength="20" name="Prizes.name" class="bajy_bb_xkgl_con3_srkd3"/>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<input type="text" maxlength="9" name="chance" class="bajy_bb_xkgl_con3_srkd3" style="width:50px;"/><span style="font-family:Courier New;font-size:20px;">/<font name="base">100000</font></span>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<select class="bajy_bb_xkgl_xlk" name="prize">
										<option value="0">是</option>
										<option value="1">否</option>
									</select>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<input type="text" maxlength="2" name="rank" value="1" class="bajy_bb_xkgl_con3_srkd3"/>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<a name="remove" onclick="cutoff(this);" style="cursor:pointer;" title="删除奖品项">
										<img width="20" src="${path}/images/main/close.png"/>
									</a>
								</td>
							</tr>
							<tr height="35px;" align="center" valign="middle" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">
									<input type="text" maxlength="20" name="Prizes.name" class="bajy_bb_xkgl_con3_srkd3"/>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<input type="text" maxlength="9" name="chance" class="bajy_bb_xkgl_con3_srkd3" style="width:50px;"/><span style="font-family:Courier New;font-size:20px;">/<font name="base">100000</font></span>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<select class="bajy_bb_xkgl_xlk" name="prize">
										<option value="0">是</option>
										<option value="1">否</option>
									</select>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<input type="text" maxlength="2" name="rank" value="2" class="bajy_bb_xkgl_con3_srkd3"/>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<a name="remove" onclick="cutoff(this);" style="cursor:pointer;" title="删除奖品项">
										<img width="20" src="${path}/images/main/close.png"/>
									</a>
								</td>
							</tr>
							<tr height="35px;" align="center" valign="middle" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">
									<input type="text" maxlength="20" name="Prizes.name" class="bajy_bb_xkgl_con3_srkd3"/>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<input type="text" maxlength="9" name="chance" class="bajy_bb_xkgl_con3_srkd3" style="width:50px;"/><span style="font-family:Courier New;font-size:20px;">/<font name="base">100000</font></span>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<select class="bajy_bb_xkgl_xlk" name="prize" id="prize">
										<option value="0">是</option>
										<option value="1">否</option>
									</select>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<input type="text" maxlength="2" name="rank" value="3" class="bajy_bb_xkgl_con3_srkd3"/>
								</td>
								<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
									<a name="remove" onclick="cutoff(this);" style="cursor:pointer;" title="删除奖品项">
										<img width="20" src="${path}/images/main/close.png"/>
									</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="zxxg_zhsz fl">
					<div class="zxxg_zhsz1" style="padding-top:10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr align="center" valign="middle">
								<td valign="middle" align="right"  width="102">活动描述： </td>
								<td valign="middle" align="left" colspan="3">
									<label>
										<textarea class="zhsz_1_wb" id="descr" name="descr" maxlength="100"></textarea>
										<label style="color: red;">*</label>
									</label>
								</td>
							</tr>
							<tr align="center" valign="middle" style="line-height: 60px;">
								<td align="center" valign="middle" colspan="4">
									<label style="float: left;line-height: 60px;height: 60px;width: 50%;">
										<input type="submit" class="khgl_cont_bc_an" value="保&nbsp;存"/>
									</label>
									<label style="float: left;line-height: 60px;height:45px;padding-top:15px;width: 50%;">
										<a style="display: block;color: white;" href="${referer}" class="bajy_bb_xkgl_btn_black">返&nbsp;&nbsp;回</a>
									</label>
								</td>
							</tr>
						</table>
					</div>
				</div>
				</form>
			</div>
		</div>
		<!--cont end-->
		<div class="foot">
			<div class="foot1">北京美谷科技有限公司 版权所有2014 京ICP备 01214990号-1</div>
		</div>
	</div>
	
	<script id="prizes_temp" type="text/html">
		<tr height="35px;" align="center" valign="middle" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
			<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;border-left: 1px dashed #cbcbcb;">
				<input type="text" maxlength="20" name="Prizes.name" class="bajy_bb_xkgl_con3_srkd3"/>
			</td>
			<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
				<input type="text" maxlength="9" name="chance" class="bajy_bb_xkgl_con3_srkd3" style="width:50px;"/>
				<span style="font-family:Courier New;font-size:20px;">/<font name="base">[base]</font></span>
			</td>
			<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
				<select class="bajy_bb_xkgl_xlk" name="prize">
					<option value="0">是</option>
					<option value="1">否</option>
				</select>
			</td>
			<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
				<input type="text" maxlength="2" name="rank" value="[rank]" class="bajy_bb_xkgl_con3_srkd3"/>
			</td>
			<td valign="middle" align="center" style="border-bottom: 1px solid #bfbfbf;border-right: 1px dashed #cbcbcb;">
				<a name="remove" onclick="cutoff(this);" style="cursor:pointer;" title="删除奖品项">
					<img width="20" src="${path}/images/main/close.png"/>
				</a>
			</td>
		</tr>
	</script>
</body>
</html>