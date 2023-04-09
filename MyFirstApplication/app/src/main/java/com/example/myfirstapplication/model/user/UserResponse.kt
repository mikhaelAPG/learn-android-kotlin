package com.example.myfirstapplication.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("job")
    @Expose
    var job: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null

}