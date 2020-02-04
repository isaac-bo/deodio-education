function initApi(){
   API = new API_Calls();
   API_1484_11 = new API_1484_11_Calls();
}

function API_Calls() {
    this.LMSInitialize = LMSInitialize;
    this.LMSSetValue = LMSSetValue;
    this.LMSGetValue = LMSGetValue;
    this.LMSCommit = LMSCommit;
    this.LMSFinish = LMSFinish;
    this.LMSGetLastError = LMSGetLastError;
    this.LMSGetErrorString = LMSGetErrorString;
}

function API_1484_11_Calls() {
    this.Initialize = Initialize;
    this.SetValue = SetValue;
    this.GetValue = GetValue;
    this.Commit = Commit;
    this.Terminate = Terminate;
    this.GetLastError = GetLastError;
    this.GetErrorString = GetErrorString;
}
	//1.2标准API
    function LMSInitialize(value)
    {      
    	return true;
    }

    function LMSSetValue(name, value)
    {
        LMSCommit(value);
        return "";

    }

    function LMSGetValue(name)
    {
    	var reCode="";
        return reCode;
    }

    function LMSCommit(value)
    {        
        var reCode="";

        return reCode;
    }

    function LMSFinish(value)
    {        
        //alert("LMSFinish:" + value);
        var reCode="";
        reCode = LMSCommit(value);
        //changeSCOContent();
        return reCode;
    }
    
    function LMSGetLastError()
    {
        var reCode = "0";
        return reCode;
    }
    
    function LMSGetErrorString(value)
    {
        var reCode = "";
        return reCode;
    }    
    
	//2004标准API
    function Initialize(value)
    {      
    	return true;
    }

    function SetValue(name, value)
    {
        LMSCommit(value);
        return "";

    }

    function GetValue(name)
    {
    	var reCode="";
        return reCode;
    }

    function Commit(value)
    {        
        var reCode="";

        return reCode;
    }

    function Terminate(value)
    {        
        //alert("LMSFinish:" + value);
        var reCode="";
        reCode = LMSCommit(value);
        //changeSCOContent();
        return reCode;
    }
    
    function GetLastError()
    {
        var reCode = "0";
        return reCode;
    }
    
    function GetErrorString(value)
    {
        var reCode = "";
        return reCode;
    }     
    
    

    //通过ajax获得服务器当前时间
    function GetServerTime() {
        var start = "";
        return start;
    }


function doConfirm(){
    if( confirm("现在退出学习记录将不能保存.  确认退出吗?") ){
       window.parent.frames[4].document.location.href = "LMSMenu.jsp";
    }else{
    }
}

function closeWin(){
 //alert("You have closed the window.22222");
 //parent.opener.location.reload();
}