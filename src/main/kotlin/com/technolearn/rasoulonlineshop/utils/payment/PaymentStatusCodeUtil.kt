package com.technolearn.rasoulonlineshop.utils.payment

class PaymentStatusCodeUtil {

    companion object {
        fun getMessageError(code: Int): String {
            return when (code) {
                0 -> "پرداخت تکمیل و با موفقیت انجام شده است"
                -1 -> "منتظر ارسال تراکنش و ادامه پرداخت"
                -2 -> "پرداخت رد شده توسط کاربر یا بانک"
                -3 -> "پرداخت در حال انتظار جواب بانک"
                -4 -> "پرداخت لغو شده است"
                -20 -> "کد api_key ارسال نشده است"
                -21 -> " کد trans_id ارسال نشده است"
                -22 -> " مبلغ ارسال نشده"
                -23 -> " لینک ارسال نشده"
                -24 -> " مبلغ صحیح نیست"
                -25 -> " تراکنش قبلا انجام و قابل ارسال نیست"
                -26 -> " مقدار توکن ارسال نشده است"
                -27 -> " شماره سفارش صحیح نیست"
                -28->" مقدار فیلد سفارشی [custom_json_fields] از نوع json نیست"
                -29->" کد بازگشت مبلغ صحیح نیست"
                -30->" مبلغ کمتر از حداقل پرداختی است"
                -31->" صندوق کاربری موجود نیست"
                -32->" مسیر بازگشت صحیح نیست"
                -33->" کلید مجوز دهی صحیح نیست"
                -34->" کد تراکنش صحیح نیست"
                -35->" ساختار کلید مجوز دهی صحیح نیست"
                -36->" شماره سفارش ارسال نشد است"
                -37->" شماره تراکنش یافت نشد"
                -38->" توکن ارسالی موجود نیست"
                -39->" کلید مجوز دهی موجود نیست"
                -40->" کلید مجوزدهی مسدود شده است"
                -41->" خطا در دریافت پارامتر، شماره شناسایی صحت اعتبار که از بانک ارسال شده موجود نیست"
                -42->" سیستم پرداخت دچار مشکل شده است"
                -43->" درگاه پرداختی برای انجام درخواست یافت نشد"
                -44->" پاسخ دریاف شده از بانک نامعتبر است"
                -45->" سیستم پرداخت غیر فعال است"
                -46->" درخواست نامعتبر"
                -47->" کلید مجوز دهی یافت نشد [حذف شده]"
                -48->" نرخ کمیسیون تعیین نشده است"
                -49->" تراکنش مورد نظر تکراریست"
                -50->" حساب کاربری برای صندوق مالی یافت نشد"
                -51->" شناسه کاربری یافت نشد"
                -52->" حساب کاربری تایید نشده است"
                -60->" ایمیل صحیح نیست"
                -61->" کد ملی صحیح نیست"
                -62->" کد پستی صحیح نیست"
                -63->" آدرس پستی صحیح نیست و یا بیش از ۱۵۰ کارکتر است"
                -64->" توضیحات صحیح نیست و یا بیش از ۱۵۰ کارکتر است"
                -65->" نام و نام خانوادگی صحیح نیست و یا بیش از ۳۵ کاکتر است"
                -66->" تلفن صحیح نیست"
                -67->" نام کاربری صحیح نیست یا بیش از ۳۰ کارکتر است"
                -68->" نام محصول صحیح نیست و یا بیش از ۳۰ کارکتر است"
                -69->" آدرس ارسالی برای بازگشت موفق صحیح نیست و یا بیش از ۱۰۰ کارکتر است"
                -70->" آدرس ارسالی برای بازگشت ناموفق صحیح نیست و یا بیش از ۱۰۰ کارکتر است"
                -71->" موبایل صحیح نیست"
                -72->" بانک پاسخگو نبوده است لطفا با نکست پی تماس بگیرید"
                -73->" مسیر بازگشت دارای خطا میباشد یا بسیار طولانیست"
                -90->" بازگشت مبلغ بدرستی انجام شد"
                -91->" عملیات ناموفق در بازگشت مبلغ"
                -92->" در عملیات بازگشت مبلغ خطا رخ داده است"
                -93->" موجودی صندوق کاربری برای بازگشت مبلغ کافی نیست"
                -94->" کلید بازگشت مبلغ یافت نشد"
                else -> "UNKNOWN ERROR"
            }
        }
        fun getMessage(code: Int): String {
            return when (code) {
                0 -> "درحال حاضر درگاه بانکی قطع شده و مشکل بزودی برطرف می شود"
                -1 -> "API Key ارسال نمی شود"
                -2 -> "Token ارسال نمی شود"
                -3 -> "API Key ارسال شده اشتباه است"
                -4 -> "امکان انجام تراکنش برای این پذیرنده وجود ندارد"
                -5 -> "تراکنش با خطا مواجه شده است"
                -6 -> "تراکنش تکراریست یا قبلا انجام شده"
                -7 -> "مقدار Token ارسالی اشتباه است"
                -8 -> "شماره تراکنش ارسالی اشتباه است"
                -9 -> "زمان مجاز برای انجام تراکنش تمام شده"
                -10 -> "مبلغ تراکنش ارسال نمی شود"
                -11 -> "مبلغ تراکنش باید به صورت عددی و با کاراکترهای لاتین باشد"
                -12 -> "مبلغ تراکنش می بایست عددی بین 10,000 و 500,000,000 ریال باشد"
                -13 -> "مقدار آدرس بازگشتی ارسال نمی شود"
                -14 -> "آدرس بازگشتی ارسالی با آدرس درگاه ثبت شده در شبکه پرداخت پی یکسان نیست"
                -15 -> "امکان وریفای وجود ندارد. این تراکنش پرداخت نشده است"
                -16 -> "یک یا چند شماره موبایل از اطلاعات پذیرندگان ارسال شده اشتباه است"
                -17 -> "میزان سهم ارسالی باید بصورت عددی و بین 1 تا 100 باشد"
                -18 -> "فرمت پذیرندگان صحیح نمی باشد"
                -19 -> "هر پذیرنده فقط یک سهم میتواند داشته باشد"
                -20 -> "مجموع سهم پذیرنده ها باید 100 درصد باشد"
                -21 -> "Reseller ID ارسالی اشتباه است"
                -22 -> "فرمت یا طول مقادیر ارسالی به درگاه اشتباه است"
                -23 -> "سوییچ PSP ( درگاه بانک ) قادر به پردازش درخواست نیست. لطفا لحظاتی بعد مجددا تلاش کنید"
                -24 -> "شماره کارت باید بصورت 16 رقمی، لاتین و چسبیده بهم باشد"
                -25 -> "امکان استفاده از سرویس در کشور مبدا شما وجود نداره"
                -26 -> "امکان انجام تراکنش برای این درگاه وجود ندارد"
                -27 -> "در انتظار تایید درگاه توسط شاپرک"
                -28 -> "امکان تسهیم تراکنش برای این درگاه وجود ندارد"
                else -> "Unknown Error Code"
            }
        }
    }
}