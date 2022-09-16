/*
Student name : Artem Slonko
Student number : A00257646
Program description: This program converts from one metric to another one. Type 2 in Menu to see more.
Discipline: JAV1001 App Development for Android
 */

import kotlin.system.exitProcess

private var Path : String = "km mi cm in";
private  var Weight : String = "kg lb g oz";
private  var Temperature : String = "C F K";
private var Volume : String = "L cup";
private var InputType :String = "";
private  var NewType : String = "";
private  var AllMetrics = Path + Weight + Temperature + Volume;

private var InputValue : Double = 0.0;
private var InputMetrics : String = "None";
private var NewMetrics = "None";
private var StrVal = "0";
private  var Result : Double = 0.0
fun main(args: Array<String>) {
    var Menu   = MenuNextStep(); // Main Menu of the program
    while (true){
        when(Menu.toInt()){
            0 -> {//type metrics
                ShowAllMetrics()
                println("Press Enter to continue: ")
                readln()
                Menu = MenuNextStep();
            };
            1 -> {//do conversion
                print("Type the value and metrics: ")
                try{//try needs if user type one word and press enter -- exception will occurred
                    var (a, b) = readln()!!.split(' ') // a,b are temp variables that is a - Value(str), and b - Metric(str)
                    StrVal = a
                    InputMetrics = b
                }
                catch (exception: Exception){
                    continue;
                }
                print("What type of metrics would you like convert to?\nAnswer: ")
                NewMetrics = readln();
                if (CheckSyntax()){ // syntax check OK
                    when(InputType){//Each function for certain Metrics type!
                        "Path" ->  ConvertPath()
                        "Weight" -> ConvertWeight()
                        "Temperature" -> ConvertTemp()
                        "Volume" -> ConvertVolume()
                    }
                }
                println("Press Enter to continue: ")
                readln()
                Menu = MenuNextStep();
            }

            2 -> {//type instruction help
                println("\t\tInstructions:\n1. Due to convert Menu must be inserted as 1.\n2.Example:" +
                        "\nType the value and metrics: 1 km\n" +
                        "What type of metrics would you like convert to?\n" +
                        "Answer: in ")
                println("Press Enter to continue: ")
                readln()
                Menu = MenuNextStep();
            }
            3 -> {//exit
                println("\nThank you for your usage of HARDroid Conversion! :)")
                exitProcess(0)
            }
        }
    }
}


//===============FUNCTIONS===========//
public fun ShowMenu(){
    println("\t\tMenu is:" +
            "\n0 - Show valid metrics" +
            "\n1 - New conversion" +
            "\n2 - Help" +
            "\n3 - Exit")
}
public fun ShowAllMetrics() {
    println("Enable Metrics for you are:\n" +
            "\tkm" +
            "\tmi" +
            "\tcm" +
            "\tin" +
            "\tlb" +
            "\tkg" +
            "\tg" +
            "\toz" +
            "\tC" +
            "\tF" +
            "\tK" +
            "\tL" +
            "\tcup");
}

public  fun HasMetrics(metric:String):Boolean{
    return AllMetrics.contains(metric)
}
public fun DefineTypeOfMetrics(InMetrics : String):String{
    //Defines whether Path,Weight,Temperature,Volume

    var OutMetrics : String = "None";
    if(Path.contains(InMetrics)){
        OutMetrics = "Path"
    }
    else if (Weight.contains(InMetrics)){
        OutMetrics = "Weight"
    }
    else if (Temperature.contains(InMetrics)){
        OutMetrics = "Temperature"
    }
    else if (Volume.contains(InMetrics)){
        OutMetrics = "Volume"
    }
    return OutMetrics;
}

fun GiveListOfMetrics(metric:String):String{
    if(Path.contains(metric)) return Path;
    else if (Weight.contains(metric)) return Weight
    else if (Temperature.contains(metric)) return  Temperature
    else if (Volume.contains(metric)) return  Volume
    else return "None"
}

fun IsItPossibleToConvert(metric1:String,metric2:String):Boolean{
    InputType = DefineTypeOfMetrics(metric1);
    NewType = DefineTypeOfMetrics(metric2);

    var Possibility : Boolean = InputType.equals(NewType)
    if(!Possibility){
        println("\t\t- You can't convert from "+metric1+" to "+metric2+"!!!");
        println("\t\tFrom "+metric1+" you can convert to "+GiveListOfMetrics(metric1))
    }
    return  Possibility
}

fun CheckSyntax():Boolean{
    var SyntaxOK = false; // all syntax OK
    var IsDigit = false; // Input value is digit not String
    var InMetricsOK = false; // Does InMetrics exist?
    var NewMetricsOK = false;// Does NewMetrics exist?
    var IsPossibleConvert = false; // can you convert from kg to g? NO!!!!

    println("\t\tERRORS LIST:")
    try{
        InputValue = StrVal.toDouble(); //IsDigit?
        IsDigit = true;
    }
    catch (exception: java.lang.Exception){//type an error
        println("\t\t- Can't convert chars to float value ");
    }

    if(HasMetrics(InputMetrics)){ // do we have such metrics?
        InMetricsOK = true;
    }
    else{//type error
        println("\t\t- Input metric is incorrect");
    }

    if (HasMetrics(NewMetrics)){// do we have such metrics?
        NewMetricsOK = true
    }else{
        println("\t\t- A metric which you are going to convert to incorrect")
    }

    if(IsItPossibleToConvert(InputMetrics,NewMetrics)){//
        IsPossibleConvert = true
    }

    SyntaxOK = IsDigit && InMetricsOK && NewMetricsOK && IsPossibleConvert;
    System.out.println(if(SyntaxOK)  ("\t\t-No Errors\n\t\t-Converstion is possible!✔") else  (""))
    return SyntaxOK;
}

fun ConvertPath() {
    var KM: Double = InputValue;
    //lets convert to km
    when (InputMetrics) {
        "km" -> KM *= 1
        "mi" -> KM *= 1.609344
        "cm" -> KM /= 100000
        "in" -> KM /= 39370.1
    }
    //then convert to certain metric
    when (NewMetrics) {
        "km" -> Result = KM
        "mi" -> Result = KM / 1.609344
        "cm" -> Result = KM * 100000
        "in" -> Result = KM * 39370.1
    }
    println(InputValue.toString() + " " + InputMetrics + " = " + Result.toString() + " " + NewMetrics)
}


fun  ConvertWeight(){
    var KG: Double = InputValue;
    //lets convert to kg
    when (InputMetrics) {
        "kg" -> KG *= 1
        "lb" -> KG *= 0.4535924
        "g" -> KG /= 1000
        "oz" -> KG *= 0.02834952
    }
    when (NewMetrics) {
        "kg" -> Result = KG
        "lb" -> Result = KG * 2.204623
        "g" -> Result = KG * 1000
        "oz" -> Result = KG * 35.27396
    }
    println(InputValue.toString() + " " + InputMetrics + " = " + Result.toString() + " " + NewMetrics)
}

fun ConvertTemp(){
    var C : Double = InputValue;
    //lets convert to C
    when (InputMetrics) {
        "C" -> C *= 1
        "F" -> C = (InputValue-32)*5/9
        "K" -> C -= 273.15
    }
    when (NewMetrics) {
        "C" -> Result = C
        "F" -> Result = (C*9/5)+32
        "K" -> Result = C + 273.15
    }
    println(InputValue.toString() + " " + InputMetrics + " = " + Result.toString() + " " + NewMetrics)
}

fun ConvertVolume(){
    var L : Double = InputValue;
    when(InputMetrics){
        "L" -> L*=1
        "cup" -> L *= 0.24
    }
    when(NewMetrics){
        "L" -> Result = L
        "cup" -> Result = L * 4.17
    }
    println(InputValue.toString() + " " + InputMetrics + " = " + Result.toString() + " " + NewMetrics)
}

fun ValidMenu(num: String) : Boolean{
    var digit = false;
    var range = false;
    var OK = false;
    try{
        var Num = num.toInt();
        digit = true;
        range = Num < 4 && Num >=0; // indexes of Main Menu
    }
    catch (exception: java.lang.Exception){
        digit = false
        range = false;
    }
    OK = range && digit
    return  OK
}

fun MenuNextStep():Int{
    var Menu = "";
    do{
        println()
        ShowMenu();
        print("What is your next step?\nMenu: ")
        Menu = readln()
    }while(!ValidMenu(Menu))
    return Menu.toInt()
}