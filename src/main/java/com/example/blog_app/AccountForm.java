package com.example.blog_app;

public class AccountForm {  //フォーム入力

    private String username;
    private String password;
    private String profileText;

    public String getUsername() { 
      return username;
    }
    public void setUsername(String username) { 
      this.username = username;
    }

    public String getPassword() { 
      return password;
    }
    public void setPassword(String password) { 
      this.password = password;
    }

    public String getProfileText() { 
      return profileText;
    }
    public void setProfileText(String profileText) { 
      this.profileText = profileText;
    }
}
