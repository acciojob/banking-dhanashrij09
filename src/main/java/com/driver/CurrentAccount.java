package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(!isNumValid(tradeLicenseId)){
            String rearrangedId = arrangeString(tradeLicenseId);
            if(rearrangedId == ""){
                throw new Exception("Valid License can not be generated");
            }
            else{
                this.tradeLicenseId = rearrangedId;
            }
        }

    }

    public boolean isNumValid(String tradeLicenseId){
        for(int i=0; i<tradeLicenseId.length()-1; i++){
            if(tradeLicenseId.charAt(i) != tradeLicenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

    public String arrangeString(String tradeLicenseId){
        int n = tradeLicenseId.length();

        int freq [] = new int[26];
        for(int i=0; i<26; i++){
            freq[i] = 0;
        }
        for(char ch : tradeLicenseId.toCharArray()){
            freq[(int)ch - (int)'A']++;
        }

        char maxChar = getCharCnt(freq);
        int maxCnt = freq[(int)maxChar - (int)'A'];

        if(maxCnt > (n+1)/2){
            return "";
        }
        String ans = "";
        for(int i=0; i<n; i++){
            ans += ' ';
        }
        int index = 0;
        while(maxCnt > 0){
            ans = ans.substring(0,index) + maxChar + ans.substring(index + 1);
            index = index + 2;
            maxCnt--;
        }
        freq[(int)maxChar - (int)'A'] = 0;
        for(int i=0; i<26; i++){
          while(freq[i] > 0 ){
              index = (index >= n) ? 1 : index ;
              ans = ans.substring(0,index) + (char)((int)'A' + 1) + ans.substring(index + 1);
              index += 2;
              freq[i]--;
          }
        }
        return ans;
    }

    public char getCharCnt(int freq []){
        int max = 0;
        char ch = 0;
        for(int i=0; i<26; i++){
           if(freq[i] > max){
               max = freq[i];
               ch = (char)((int)'A' + i);
           }
        }
        return ch;
    }
}
