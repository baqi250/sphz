/**
 * Created by hongqing on 17-3-16.
 */
define(["jquery/jquery","toastr/toastr.min"],function(jquery,toastr){
    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "7000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }
    $.Notification = {

        info:function(title,message,setting) {
            $.extend(toastr.options,setting);
            toastr.info(message,title);
        },
        error:function(title,message,setting) {
            toastr.error(message,title);

        },
        warn:function(title,message,setting) {
            toastr.warning(message,title);
        },
        success:function(title,message,setting) {
            toastr.success(message,title);
        }
    };
})