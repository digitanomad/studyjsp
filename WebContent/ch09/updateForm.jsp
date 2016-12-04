<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="style.css"/>

<form method="post" action="updatePro.jsp">
	<table>
		<tr>
      		<td class="label"><label for="idt">아이디</label>
      		<td class="content"><input id="idt" name="idt" type="text" size="20" 
          		maxlength="50" placeholder="example@kings.com" autofocus required>
    	</tr>
    	<tr>
      		<td class="label"><label for="passwd">비밀번호</label>
      		<td class="content"><input id="passwd" name="passwd" type="password" 
          		size="20" placeholder="6~16자 숫자/문자" maxlength="16" required>
        </tr>
    	<tr>
      		<td class="label"><label for="name">이름</label>
      		<td class="content"><input id="name" name="name" type="text" size="20" 
                 maxlength="10" placeholder="킹도라" required>
    	</tr>
    	<tr>
      		<td class="label"><label for="name">주소</label>
      		<td class="content"><input id="addr" name="addr" type="text" size="20" 
                 maxlength="100" placeholder="서울시" required>
        </tr>
    	<tr>
      		<td class="label"><label for="name">전화번호</label>
      		<td class="content"><input id="tel" name="tel" type="text" size="20" 
                 maxlength="20" placeholder="010-1111-1111" required>
        </tr>
    	<tr>
      		<td class="label2" colspan="2"><input type="submit" value="수정완료">
         	<input type="reset" value="다시작성">
		</tr>
	</table>
</form>