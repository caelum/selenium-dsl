<html>
    <head>
        <title>Login</title>
    </head>
    <body>
    	<script type="text/javascript">
    		function validate(form) {
				if (document.getElementById('login').value == 'myLogin'
					&& document.getElementById('password').value == 'myPassword')
					return true;
				else {
					var input = document.createElement('input');
					input.type = 'hidden';
					input.name = 'errors';
					input.value = 'Invalid login!';
					form.appendChild(input);
					form.action = '';
					return true;
				} 
    		}
    	</script>
        <table id="table">
            <thead>
            	<tr>
                	<th>First Column</th>
                	<th>Second Column</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>cell_1_1</td>
                    <td>cell_1_2</td>
                </tr>
                <tr>
                    <td>cell_2_1</td>
                    <td>cell_2_2</td>
                </tr>
            </tbody>
        </table>
    
        <div id="errors">
            <!-- populated on invalid login --> 
			${param['errors'] }
        </div>
        <form id="form" action="main.html" onsubmit="return validate(this)" >
            Login: <input id="login" type="text" name="login" value="${param['remember']? param['login']:'' }"/><br/>
            Password: <input id="password" type="password" name="password" /><br/>
            Remember? <input type="checkbox" name="remember" value="true" checked=${param['remember'] }/>
            <input type="submit" value="enviar" name="enviar" />
        </form>
    </body>
</html>