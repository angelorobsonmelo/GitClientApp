package com.desafiosafaty.angelorobson.dao;


import android.app.Activity;
import android.content.SharedPreferences;

import com.desafiosafaty.angelorobson.model.Repository;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.desafiosafaty.angelorobson.constants.Constants.REPOSITORY;

public class RepositoryDAO {

 private Activity activity;

  public RepositoryDAO(Activity activity) {
    this.activity = activity;
  }

  public void save(Repository repository){
    SharedPreferences mPrefs = this.activity.getPreferences(MODE_PRIVATE);
    SharedPreferences.Editor prefsEditor = mPrefs.edit();

    Gson gson = new Gson();
    String RepositoryJson = gson.toJson(repository);

    prefsEditor.putString(REPOSITORY, RepositoryJson);
    prefsEditor.apply();
  }

  public Repository getRepository(){
    Gson gson = new Gson();
    SharedPreferences mPrefs =  this.activity.getPreferences(MODE_PRIVATE);

    String RepositoryJson = mPrefs.getString(REPOSITORY, "");
    return gson.fromJson(RepositoryJson, Repository.class);
  }

}
