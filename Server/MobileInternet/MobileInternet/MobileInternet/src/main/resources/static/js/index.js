// js
window.onload = function() {

    document.onreadystatechange = setTimeout(pageLoading, 1500);

    console.log("%c小蓝鸟项目","font-size: 24px; color: #0081C9;");
    console.log("....   ....   ....   ....\n" +
                "....   ....   ....   ....\n" +
                "....   .... . ....   ....\n" +
                "....   .... # ...... ....\n" +
                "   .... ###    #  ...#   \n" +
                "   ...#         # ... ,  \n" +
                "    .#           #...    \n" +
                "....              #  ....\n" +
                "....W         #   # .E#..\n" +
                "....t     K        W ....\n" +
                "....           ##;;;#....\n" +
                "   W       #  ##;;;;#    \n" +
                " . #      ###.## ;;;#    \n" +
                " .     ;;;  #;#   ; ..   \n" +
                ".....  ;;;;        # ....\n" +
                "....#  ;;;   #    #  .W..\n" +
                "....#   ;  # ## #.   ....\n" +
                ".... #      ##  #.   ....\n" +
                "    ... ####   i# ... j  \n" +
                "    ... #       # ...    \n" +
                "  .:... #       # ...    \n" +
                "....   .#  L   D..   ....\n" +
                "....   ...#.##....   ....\n" +
                "....   ....#  #...   ....\n" +
                "....   ....   ....   ....")
    console.log("\n" +
        "    __  ___               __                       \n" +
        "   /  |/  /__  ____ ___  / /_  ___  __________   _ \n" +
        "  / /|_/ / _ \\/ __ `__ \\/ __ \\/ _ \\/ ___/ ___/  (_)\n" +
        " / /  / /  __/ / / / / / /_/ /  __/ /  (__  )  _   \n" +
        "/_/  /_/\\___/_/ /_/ /_/_.___/\\___/_/  /____/  (_)  \n" +
        "                                                   \n" +
        "%c\tLeYe\tLau\n\tSeeChen\tLee\n\tViHang\tTan\n\tWinYi\tLee", "font-size: 1.5em; color: #0ff");
    console.log('%O', new Date());
}
function pageLoading(){

    if (document.readyState === "complete"){

        $('body:eq(0)').css({
                                "opacity" : "1",
                                "cursor"  : "auto"
                            });
        $('html:eq(0)').css('cursor', 'auto');

        $('#loadingContent').css('opacity', '0');

        setTimeout(function(){
            document.getElementById("loadingContent").style.display="none";
        },3000);
    }
}