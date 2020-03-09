let WpFileUpload = function(containerId) {
    if (WpParamTools.isNullOrEmpty(containerId)) {
        WpFileUploadMessage.error(WpFileUploadMessageModel.notExitUploadId);
        return;
    }
    let wfu = {
        "uploadId": containerId,
        "uploadUrl": "#",
        "progressUrl": "#",
        "scheduleStandard": false,
        "selfUploadBtId": "",
        "rememberUpload": false,
        "autoCommit": false,
        "isHiddenUploadBt": false,
        "isHiddenCleanBt": false,
        "isAutoClean": false,
        "canDrag": true,
        "fileType": "*",
        "size": "-1",
        "totalSize": "-1",
        "maxFileNumber": "-1",
        "ismultiple": true,
        "showSummerProgress": true,
        "showFileItemProgress": true,
        "showProgressNum": false,
        "uploadFileParam": "files",
        "uploadFileParamIteration": false,
        "resultData": null,
        "beforeUpload": function() {},
        "onUpload": function() {}
    };
    wfu.initUpload = WpFileUploadEvent.initUpload;
    wfu.showFileResult = WpFileUploadEvent.showFileResult;
    wfu.removeShowFileItem = WpFileUploadEvent.removeShowFileItem;
    wfu.getFormData = WpFileUploadFormTools.getFormData;
    wfu.getFormDataOfUploadFile = WpFileUploadFormTools.getFormDataOfUploadFile;
    wfu.uploadError = WpFileUploadTools.uploadError;
    wfu.uploadSuccess = WpFileUploadTools.uploadSuccess;
    wfu.upload = WpFileUploadTools.uploadFileOfWfuEvent;
    return wfu;
};
let WpParamTools = {
    "isNullOrEmpty": function(param) {
        return null == param || $.trim(param) === "";
    },
    "isNullOrEmptyOfArray": function(arrayObj) {
        return null == arrayObj || arrayObj.length <= 0;
    },
    "isOnlyObject": function(param) {
        return param.constructor === Object;
    }
};
let WpFileUploadEvent = {
    "initUpload": function(opt) {
        let wfu = this;
        if (null != opt) {
            if (!WpParamTools.isOnlyObject(opt)) {
                WpFileUploadMessage.error(WpFileUploadMessageModel.initConfigFormatError);
                return;
            }
            $.each(wfu, function(key) {
                if (!WpParamTools.isNullOrEmpty(opt[key])) {
                    wfu[key] = opt[key];
                }
            });
        }
        WpFileUploadEvent.initWithLayout(wfu);
        WpFileUploadEvent.initWithDrag(wfu);
        WpFileUploadEvent.initWithSelectFile(wfu);
        WpFileUploadEvent.initWithCleanFile(wfu);
        WpFileUploadEvent.initWithUpload(wfu);
        WpFileUploadFileList.initFileList(wfu);
    },
    "initWithLayout": function(wfu) {
        let uploadId = wfu.uploadId;
        let fileContanObj = $("#" + uploadId);
        fileContanObj.append(WpFileUploadViewsModel.getHeadButtonsView(wfu));
        fileContanObj.append(WpFileUploadViewsModel.getSummerProgress(wfu));
        fileContanObj.append(WpFileUploadViewsModel.getFileContainBox());
    },
    "initWithDrag": function(wfu) {
        let canDrag = wfu.canDrag;
        let uploadId = wfu.uploadId;
        let containObj = $("#" + uploadId);
        let containBoxObj = containObj.find(".box").get(0);
        if (canDrag) {
            $(document).on({
                dragleave: function(e) {
                    e.preventDefault();
                },
                drop: function(e) {
                    e.preventDefault();
                },
                dragenter: function(e) {
                    e.preventDefault();
                },
                dragover: function(e) {
                    e.preventDefault();
                }
            });
            if (containBoxObj != null) {
                containBoxObj.addEventListener("drop", function(e) {
                    if (containObj.attr("isUpload") === "true") {
                        e.preventDefault();
                    } else {
                        WpFileUploadEvent.dragListingEvent(e, wfu);
                    }
                });
            }
        }
    },
    "initWithSelectFile": function(wfu) {
        let uploadId = wfu.uploadId;
        let selectFileBtObj = $("#" + uploadId + " .uploadBts .selectFileBt");
        selectFileBtObj.css("background-color", "#0099FF");
        selectFileBtObj.off();
        selectFileBtObj.on("click", function() {
            if (wfu.autoCommit) {
                WpFileUploadEvent.cleanFileEvent(wfu);
            }
            WpFileUploadEvent.selectFileEvent(wfu);
        });
    },
    "initWithCleanFile": function(wfu) {
        let uploadId = wfu.uploadId;
        if (!wfu.isHiddenCleanBt) {
            let cleanBtObj = $("#" + uploadId + " .uploadBts .cleanFileBt");
            let cleanBtObjIcon = $("#" + uploadId + " .uploadBts .cleanFileBt i");
            cleanBtObj.off();
            cleanBtObj.on("click", function() {
                WpFileUploadEvent.cleanFileEvent(wfu);
            });
            cleanBtObjIcon.css("color", "#0099FF");
        }
    },
    "selectFileEvent": function(wfu) {
        let uploadId = wfu.uploadId;
        let ismultiple = wfu.ismultiple;
        let inputObj = document.createElement('input');
        inputObj.setAttribute('id', uploadId + '_file');
        inputObj.setAttribute('type', 'file');
        inputObj.setAttribute("style", 'visibility:hidden');
        if (ismultiple) {
            inputObj.setAttribute("multiple", "multiple");
        }
        $(inputObj).on("change", function() {
            WpFileUploadEvent.selectFileChangeEvent(this.files, wfu);
        });
        document.body.appendChild(inputObj);
        inputObj.click();
    },
    "selectFileChangeEvent": function(files, wfu) {
        WpFileUploadTools.addFileList(files, wfu);
        WpFileUploadTools.cleanFilInputWithSelectFile(wfu);
        if (wfu.autoCommit) {
            WpFileUploadTools.uploadFileEvent(wfu);
        }
    },
    "cleanFileEvent": function(wfu) {
        let uploadId = wfu.uploadId;
        if (wfu.showSummerProgress) {
            WpFileUploadTools.setProgressShow(uploadId, false);
            WpFileUploadTools.setProgressNumber(wfu, 0);
        }
        WpFileUploadTools.cleanFilInputWithSelectFile(wfu);
        WpFileUploadFileList.setFileList([], wfu);
        WpFileItemTools.getNeedUploadItemArray(uploadId).remove();
        WpFileUploadTools.changeUploadButtonsStatus(wfu, 2);
    },
    "dragListingEvent": function(wfu) {},
    "initWithUpload": function(wfu) {
        let uploadId = wfu.uploadId;
        if (!wfu.isHiddenUploadBt) {
            let uploadFileBt = $("#" + uploadId + " .uploadBts .uploadFileBt");
            uploadFileBt.off();
            uploadFileBt.on("click", function() {
                WpFileUploadTools.uploadFileEvent(wfu);
            });
            let uploadFileBtIcon = $("#" + uploadId + " .uploadBts .uploadFileBt i");
            uploadFileBtIcon.css("color", "#0099FF");
        }
        if (wfu.selfUploadBtId != null && wfu.selfUploadBtId !== "") {
            if (WpFileUploadTools.foundExitById(wfu.selfUploadBtId)) {
                let selfUploadBt = $("#" + wfu.selfUploadBtId);
                selfUploadBt.off();
                selfUploadBt.on("click", function() {
                    WpFileUploadTools.uploadFileEvent(wfu);
                });
            }
        }
    },
    "startUpload": function(wfu) {
        let uploadId = wfu.uploadId;
        $("#" + uploadId).attr("isUpload", "true")
    },
    "stopUpload": function(wfu) {
        let uploadId = wfu.uploadId;
        $("#" + uploadId).removeAttr("isUpload");
    },
    "initWithDeleteFile": function(wfu) {
        let uploadId = wfu.uploadId;
        let fileItemViewArray = WpFileItemTools.getNeedUploadItemArray(uploadId)
        let fileItemDeleteBt = WpFileItemTools.getFileViewStatus(fileItemViewArray);
        fileItemDeleteBt.off();
        fileItemDeleteBt.on("click", function() {
            WpFileUploadEvent.deleteFileEvent(wfu, this);
        })
    },
    "deleteFileEvent": function(wfu, obj) {
        let fileItem = $(obj).parent().parent();
        let fileCodeId = fileItem.attr("fileCodeId");
        let fileListArray = WpFileUploadFileList.getFileList(wfu);
        delete fileListArray[fileCodeId];
        WpFileUploadFileList.setFileList(fileListArray, wfu);
        fileItem.remove();
    },
    "showFileResult": function(fileUrl, fileId, defineFileName, deleteFile, downloadFile, deleteEvent, downLoadEvent) {
        let wfu = this;
        let uploadId = wfu.uploadId;
        if (null == fileUrl || fileUrl == "" || uploadId == null || uploadId == "") {
            return;
        }
        let boxJsObj = $("#" + uploadId + " .box").get(0);
        let fileName = defineFileName;
        if (WpParamTools.isNullOrEmpty(defineFileName)) {
            fileName = WpFileUploadTools.getFileNameWithUrl(fileUrl);
        }
        let fileType = WpFileUploadTools.getSuffixNameByFileName(fileName)
        let isImg = WpFileUploadTools.isInArray(fileType, WpFileUploadTools.imgArray);
        fileType = fileType.toUpperCase();
        let modelStr = WpFileUploadViewsModel.getFileItemResultModel(fileType, fileId, fileName, isImg, fileUrl, deleteFile, downloadFile);
        $(boxJsObj).append(modelStr);
        if (deleteFile) {
            let fileItem = WpFileItemTools.getIsUploadItem(uploadId, fileId);
            WpFileItemTools.getFileViewRemove(fileItem).mousedown(function() {
                if (deleteEvent != null && deleteEvent != "" && (typeof deleteEvent === "function")) {
                    deleteEvent(fileId);
                }
            });
        }
        if (downloadFile) {
            let fileItem = WpFileItemTools.getIsUploadItem(uploadId, fileId);
            WpFileItemTools.getFileViewDown(fileItem).mousedown(function() {
                if (deleteEvent != null && deleteEvent != "" && (typeof deleteEvent === "function")) {
                    downLoadEvent(fileId, fileUrl);
                }
            });
        }
    },
    "removeShowFileItem": function(fileId) {
        let wfu = this;
        let uploadId = wfu.uploadId;
        let fileitemObj = WpFileItemTools.getIsUploadItem(uploadId, fileId);
        fileitemObj.remove();
    }
};
let WpFileUploadViewsModel = {
    "getHeadButtonsView": function(wfu) {
        let selectFileButtonTitle = '选择文件';
        let btsStr = '';
        btsStr += '<div class="uploadBts">';
        btsStr += '<div>';
        btsStr += '<div class="selectFileBt">' + selectFileButtonTitle + '</div>';
        btsStr += '</div>';
        if (!wfu.isHiddenUploadBt) {
            btsStr += '<div class="uploadFileBt">';
            btsStr += '<i class="iconfont icon-shangchuan"></i>';
            btsStr += '</div>';
        }
        if (!wfu.isHiddenCleanBt) {
            btsStr += '<div class="cleanFileBt">';
            btsStr += '<i class="iconfont icon-qingchu"></i>';
            btsStr += '</div>';
        }
        btsStr += '</div>';
        return btsStr;
    },
    "getSummerProgress": function(wfu) {
        let summerProgressStr = '';
        let progressNum = '';
        if (wfu.showProgressNum) {
            progressNum = '0%';
        }
        if (wfu.showSummerProgress) {
            summerProgressStr += '<div class="subberProgress">';
            summerProgressStr += '<div class="progress">';
            summerProgressStr += '<div>' + progressNum + '</div>';
            summerProgressStr += '</div>';
            summerProgressStr += '</div>';
        }
        return summerProgressStr;
    },
    "getFileContainBox": function() {
        return '<div class="box"></div>';
    },
    "getFileItemModel": function(isImg, fileType, fileName, isImgUrl, fileCodeId) {
        let showTypeStr = '<div class="fileType">' + fileType + '</div> <i class="iconfont icon-wenjian"></i>';
        if (isImg) {
            if (isImgUrl != null && isImgUrl !== "null" && isImgUrl !== "") {
                showTypeStr = '<img src="' + isImgUrl + '" alt="' + fileName + '"/>';
            }
        }
        let modelStr = "";
        modelStr += '<div class="fileItem" fileCodeId="' + fileCodeId + '">';
        modelStr += '<div class="imgShow">';
        modelStr += showTypeStr;
        modelStr += '</div>';
        modelStr += '<div class="progress">';
        modelStr += '<div class="progress_inner"></div>';
        modelStr += '</div>';
        modelStr += '<div class="status">';
        modelStr += '<i class="iconfont icon-shanchu"></i>';
        modelStr += '</div>';
        modelStr += '<div class="fileName">';
        modelStr += fileName;
        modelStr += '</div>';
        modelStr += '</div>';
        return modelStr;
    },
    "getFileItemResultModel": function(fileType, fileId, fileName, isImg, fileUrl, deleteFile, downloadFile) {
        let showTypeStr = '<div class="fileType">' + fileType + '</div> <i class="iconfont icon-wenjian"></i>';
        if (isImg) {
            if (fileUrl != null && fileUrl != "null" && fileUrl != "") {
                showTypeStr = '<img src="' + fileUrl + '"/>';
            }
        }
        let showImgStyle = 'imgShow';
        if (!deleteFile) {
            showImgStyle += " imgShowResult";
        }
        let modelStr = '';
        modelStr += '<div class="fileItem" showFileCode="' + fileId + '">';
        modelStr += '<div class="' + showImgStyle + '">';
        modelStr += showTypeStr;
        modelStr += '</div>';
        if (downloadFile) {
            modelStr += '<div class="down">';
            modelStr += '<i class="iconfont icon-xiazai"></i>';
            modelStr += '</div>';
        }
        if (deleteFile) {
            modelStr += '<div class="status">';
            modelStr += '<i class="iconfont icon-shanchu"></i>';
            modelStr += '</div>';
        }
        modelStr += '<div class="fileName">';
        modelStr += fileName;
        modelStr += '</div>';
        modelStr += '</div>';
        return modelStr;
    }
};
let WpFileUploadAjax = {
    "startUploadFile": function(wfu, formData, rememberFile) {
        $.ajax({
            type: "post",
            url: wfu.uploadUrl,
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
                WpFileUploadFileList.flushRememberFile(rememberFile, wfu);
                wfu.resultData = data;
                setTimeout(function() {
                    wfu.onUpload()
                }, 500);
                if (wfu.isAutoClean) {
                    setTimeout(function() {
                        WpFileUploadEvent.cleanFileEvent(wfu);
                    }, 2000);
                }
            },
            error: function(e) {
                wfu.uploadError();
            }
        });
    }
};
let WpFileUploadFileList = {
    "initFileList": function(wfu) {
        wfu.fileList = new Array();
    },
    "getFileList": function(wfu) {
        if (null == wfu.fileList) {
            WpFileUploadFileList.initFileList(wfu);
        }
        return wfu.fileList;
    },
    "setFileList": function(fileList, wfu) {
        wfu.fileList = fileList;
    },
    "flushRememberFile": function(fileList, wfu) {
        if (wfu.rememberUpload) {
            let rememberFileIsEmpty = wfu.rememberFile == null || wfu.rememberFile === "" || wfu.rememberFile.length === 0;
            if (rememberFileIsEmpty) {
                wfu.rememberFile = wfu.fileList;
            } else {
                let rememberFileArray = wfu.rememberFile;
                for (let i = 0; i < fileList.length; i++) {
                    rememberFileArray[rememberFileArray.length] = fileList[i];
                }
                wfu.rememberFile = rememberFileArray;
            }
        }
    },
    "getFilesDataAmount": function(wfu) {
        let fileList = WpFileUploadFileList.getFileList(wfu);
        let summer = 0;
        for (let i = 0; i < fileList.length; i++) {
            let fileItem = fileList[i];
            if (fileItem != null) {
                summer = parseFloat(summer) + fileItem.size;
            }
        }
        return summer;
    },
    "getFileNumber": function(wfu) {
        let number = 0;
        let fileList = WpFileUploadFileList.getFileList(wfu);
        for (let i = 0; i < fileList.length; i++) {
            if (fileList[i] != null) {
                number++;
            }
        }
        return number;
    },
    "fileIsExit": function(file, wfu) {
        let fileList = WpFileUploadFileList.getFileList(wfu);
        let ishave = false;
        for (let i = 0; i < fileList.length; i++) {
            let fileItem = fileList[i];
            if (null != fileItem && fileItem.name === file.name && fileItem.size === file.size) {
                ishave = true;
            }
        }
        return ishave;
    },
    "fileIsHaveUpload": function(file, wfu) {
        let fileList = wfu.rememberFile;
        let ishave = false;
        if (fileList != null) {
            for (let i = 0; i < fileList.length; i++) {
                let fileItem = fileList[i];
                if (null != fileItem && fileItem.name === file.name && fileItem.size === file.size) {
                    ishave = true;
                }
            }
        }
        return ishave;
    },
    "getFileTotalSize": function(wfu) {
        let fileList = WpFileUploadFileList.getFileList(wfu);
        let totalSize = 0;
        for (let i = 0; i < fileList.length; i++) {
            let fileItem = fileList[i];
            if (null != fileItem) {
                totalSize += parseInt(fileItem.size);
            }
        }
        return totalSize;
    },
};
let WpFileUploadTools = {
    "imgArray": ['jpg', 'png', 'jpeg', 'bmp', 'gif', 'webp'],
    "setProgressShow": function(uploadId, isShow) {
        let subberProgressParent = $("#" + uploadId + " .subberProgress");
        let display = isShow ? "block" : "none";
        subberProgressParent.css("display", display);
    },
    "setProgressNumber": function(wfu, percentNum) {
        let uploadId = wfu.uploadId;
        let subberProgress = $("#" + uploadId + " .subberProgress .progress>div");
        percentNum = percentNum + "%";
        subberProgress.css("width", percentNum);
        if (wfu.showProgressNum) {
            subberProgress.html(percentNum);
        }
    },
    "addFileList": function(fileList, wfu) {
        let uploadId = wfu.uploadId;
        let boxJsObj = $("#" + uploadId + " .box").get(0);
        let fileListArray = WpFileUploadFileList.getFileList(wfu);
        let fileNumber = WpFileUploadFileList.getFileNumber(wfu);
        let isOutOfFileNumber = wfu.maxFileNumber !== "-1" && ((parseInt(fileNumber) + fileList.length) > parseInt(wfu.maxFileNumber));
        if (isOutOfFileNumber) {
            WpFileUploadMessage.error(WpFileUploadMessageModel.outMaxFileNumber(wfu.maxFileNumber));
            return;
        }
        let imgtest = /image\/(\w)*/;
        let fileTypeArray = wfu.fileType;
        let fileSizeLimit = wfu.size;
        let fileTotalSizeLimit = wfu.totalSize;
        for (let i = 0; i < fileList.length; i++) {
            let fileItem = fileList[i];
            if (WpFileUploadFileList.fileIsExit(fileItem, wfu)) {
                WpFileUploadMessage.error(WpFileUploadMessageModel.fileIsExit(fileItem.name));
                continue;
            }
            if (wfu.rememberUpload) {
                if (WpFileUploadFileList.fileIsHaveUpload(fileItem, wfu)) {
                    WpFileUploadMessage.error(WpFileUploadMessageModel.fileIsHaveUpload(fileItem.name));
                    continue;
                }
            }
            let isOutOfTotalSize = fileTotalSizeLimit !== '-1' && (WpFileUploadFileList.getFileTotalSize(wfu) + fileItem.size > (fileTotalSizeLimit * 1000));
            if (isOutOfTotalSize) {
                WpFileUploadMessage.error(WpFileUploadMessageModel.outOfTotalSize(fileTotalSizeLimit));
                continue;
            }
            let isOutOfSize = fileSizeLimit !== '-1' && fileItem.size > (fileSizeLimit * 1000);
            if (isOutOfSize) {
                WpFileUploadMessage.error(WpFileUploadMessageModel.outOfSize(fileItem.name, fileSizeLimit));
                continue;
            }
            let fileTypeStr = WpFileUploadTools.getSuffixNameByFileName(fileItem.name);
            let fileIsInType = fileTypeArray === "*" || WpFileUploadTools.isInArray(fileTypeStr, fileTypeArray);
            if (fileIsInType) {
                let fileTypeUpcaseStr = fileTypeStr.toUpperCase();
                let fileModel = "";
                if (imgtest.test(fileItem.type)) {
                    let imgUrlStr = WpFileUploadTools.getImgUrlOfLocal(fileItem);
                    fileModel = WpFileUploadViewsModel.getFileItemModel(true, fileTypeUpcaseStr, fileItem.name, imgUrlStr, fileListArray.length);
                } else {
                    fileModel = WpFileUploadViewsModel.getFileItemModel(false, fileTypeUpcaseStr, fileItem.name, null, fileListArray.length);
                }
                $(boxJsObj).append(fileModel);
                WpFileUploadEvent.initWithDeleteFile(wfu);
                fileListArray[fileListArray.length] = fileList[i];
            } else {
                WpFileUploadMessage.error(WpFileUploadMessageModel.notSupperFileType(fileItem.name));
            }
        }
        WpFileUploadFileList.setFileList(fileListArray, wfu);
    },
    "cleanFilInputWithSelectFile": function(wfu) {
        let uploadId = wfu.uploadId;
        $("#" + uploadId + "_file").remove();
    },
    "uploadFileEvent": function(wfu) {
        if (null != wfu.beforeUpload && (typeof wfu.beforeUpload === "function")) {
            wfu.beforeUpload();
        }
        WpFileUploadTools.uploadFile(wfu);
    },
    "uploadFileOfWfuEvent": function() {
        let wfu = this;
        WpFileUploadTools.uploadFileEvent(wfu);
    },
    "foundExitById": function(id) {
        return $("#" + id).size() > 0;
    },
    "getSuffixNameByFileName": function(fileName) {
        let str = fileName;
        let index = str.lastIndexOf(".");
        if (index < 0) {
            return "未知";
        }
        let pos = index + 1;
        return str.substring(pos, str.length);
    },
    "isInArray": function(strFound, arrays) {
        let ishave = false;
        for (let i = 0; i < arrays.length; i++) {
            if (strFound === arrays[i] || strFound.toLowerCase() === arrays[i]) {
                ishave = true;
                break;
            }
        }
        return ishave;
    },
    "getImgUrlOfLocal": function(fileItem) {
        let imgUrlStr = "";
        if (window.createObjectURL !== undefined) {
            imgUrlStr = window.createObjectURL(fileItem);
        } else if (window.URL !== undefined) {
            imgUrlStr = window.URL.createObjectURL(fileItem);
        } else if (window.webkitURL !== undefined) {
            imgUrlStr = window.webkitURL.createObjectURL(fileItem);
        }
        return imgUrlStr;
    },
    "addUploadFileToFormData": function(wfu, formData, rememberFile) {
        let fileList = WpFileUploadFileList.getFileList(wfu);
        let paramNum = 0;
        for (let i = 0; i < fileList.length; i++) {
            if (fileList[i] == null) {
                continue;
            }
            let fileItem = fileList[i];
            if (!wfu.uploadFileParamIteration) {
                formData.append(wfu.uploadFileParam, fileItem);
            } else {
                formData.append(wfu.uploadFileParam + paramNum++, fileItem);
            }
            WpFileUploadTools.addUploadFileRemember(wfu, rememberFile, fileItem);
        }
    },
    "addUploadFileRemember": function(wfu, rememberFiles, fileItem) {
        if (wfu.rememberFile && null != rememberFiles && null != fileItem) {
            rememberFiles[rememberFiles.length] = fileItem;
        }
    },
    "addUploadParamToFormData": function(wfu, formData) {
        if (null != wfu.otherData && wfu.otherData != "") {
            for (let j = 0; j < wfu.otherData.length; j++) {
                formData.append(wfu.otherData[j].name, wfu.otherData[j].value);
            }
        }
    },
    "uploadFile": function(wfu) {
        WpFileUploadEvent.startUpload(wfu);
        let uploadUrl = wfu.uploadUrl;
        let rememberFile = [];
        let formData = new FormData();
        let fileNumber = WpFileUploadFileList.getFileNumber(wfu);
        if (fileNumber <= 0) {
            WpFileUploadMessage.info(WpFileUploadMessageModel.noFileUpload);
            return;
        }
        WpFileUploadTools.addUploadFileToFormData(wfu, formData, rememberFile);
        WpFileUploadTools.addUploadParamToFormData(wfu, formData);
        if (uploadUrl !== "#" && uploadUrl != "") {
            WpFileUploadTools.changeUploadButtonsStatus(wfu, 0);
            WpFileUploadAjax.startUploadFile(wfu, formData, rememberFile);
        } else if (wfu.scheduleStandard) {
            WpFileUploadMessage.warn(WpFileUploadMessageModel.noUploadUrl);
            WpFileUploadTools.changeUploadButtonsStatus(wfu, 0);
            WpFileUploadFileList.flushRememberFile(rememberFile, wfu);
        }
        WpFileUploadTools.getFileUploadProgressMsg(wfu);
    },
    "getFileUploadProgressMsg": function(wfu) {
        let uploadId = wfu.uploadId;
        let progressUrl = wfu.progressUrl;
        if (wfu.showSummerProgress) {
            $("#" + uploadId + " .subberProgress").css("display", "block");
        } else if (!wfu.showFileItemProgress) {
            return;
        }
        let fileItemView = WpFileItemTools.getNeedUploadItemArray(uploadId);
        WpFileItemTools.getFileViewStatus(fileItemView).removeClass();
        if (!wfu.scheduleStandard && progressUrl !== "#" && progressUrl != null && progressUrl !== "") {
            let getCount = 20;
            let intervalId = setInterval(function() {
                $.get(progressUrl, {}, function(data, status) {
                    let percent = data.percent;
                    let bytesRead = data.bytesRead;
                    let items = data.items;
                    if (percent >= 100) {
                        clearInterval(intervalId);
                        percent = 100;
                        WpFileUploadEvent.initWithCleanFile(wfu);
                    }
                    if (getCount <= 0) {
                        clearInterval(intervalId);
                        wfu.uploadError();
                        return;
                    } else {
                        if (bytesRead == 0) {
                            getCount--;
                        }
                    }
                    WpFileUploadTools.showUploadProgress(wfu, bytesRead, percent, items);
                }, "json");
            }, 500);
        } else {
            let filesDataAmount = WpFileUploadFileList.getFilesDataAmount(wfu);
            let percent = 0;
            let bytesRead = 0;
            if (wfu.scheduleStandard) {
                let speedSchedule = WpFileUploadComputer.div(filesDataAmount, 10);
                let intervalId = setInterval(function() {
                    bytesRead = WpFileUploadComputer.add(bytesRead, speedSchedule);
                    percent = WpFileUploadComputer.div(bytesRead, filesDataAmount) * 100;
                    percent = percent.toFixed(2);
                    if (percent >= 100) {
                        clearInterval(intervalId);
                        percent = 100;
                        WpFileUploadEvent.initWithCleanFile(wfu);
                    }
                    WpFileUploadTools.showUploadProgress(wfu, bytesRead, percent);
                }, 500);
            } else {
                bytesRead = 0.6 * filesDataAmount;
                percent = 60;
                WpFileUploadEvent.initWithCleanFile(wfu);
                WpFileUploadTools.showUploadProgress(wfu, bytesRead, percent);
            }
        }
    },
    "showUploadProgress": function(wfu, bytesRead, percent, items) {
        let fileListArray = WpFileUploadFileList.getFileList(wfu);
        if (wfu.showSummerProgress) {
            WpFileUploadTools.setProgressNumber(wfu, percent);
            if (percent == 100) {
                if (wfu.isAutoClean) {
                    setTimeout(function() {
                        WpFileUploadEvent.cleanFileEvent(wfu);
                    }, 2000);
                }
            }
        }
        if (wfu.showFileItemProgress) {
            if (items == null) {
                WpFileUploadTools.showProgramWithNoItem(wfu, bytesRead, percent, fileListArray);
            } else {
                WpFileUploadTools.showProgramWithItem(wfu, bytesRead, percent, fileListArray, items);
            }
        } else {
            let uploadId = wfu.uploadId;
            let fileItemView = WpFileItemTools.getNeedUploadItemArray(uploadId);
            let fileItemStatus = WpFileItemTools.getFileViewStatus(fileItemView);
            fileItemStatus.off();
            fileItemStatus.addClass("iconfont icon-gou");
            WpFileUploadTools.changeUploadButtonsStatus(wfu, 1);
        }
    },
    "showProgramWithNoItem": function(wfu, bytesRead, percent, fileListArray) {
        for (let i = 0; i < fileListArray.length; i++) {
            if (fileListArray[i] == null) {
                continue;
            }
            let testbytesRead = bytesRead - fileListArray[i].size;
            if (testbytesRead < 0) {
                if (percent == 100) {
                    WpFileUploadTools.setFileItemProgress(wfu, i, 100, 1);
                    bytesRead = bytesRead - fileListArray[i].size;
                } else {
                    let fileItemPercent = bytesRead / fileListArray[i].size * 100;
                    WpFileUploadTools.setFileItemProgress(wfu, i, fileItemPercent, 0);
                    break;
                }
            } else if (testbytesRead >= 0) {
                WpFileUploadTools.setFileItemProgress(wfu, i, 100, 0);
                bytesRead = bytesRead - fileListArray[i].size;
            }
        }
    },
    "showProgramWithItem": function(wfu, bytesRead, percent, fileListArray, items) {
        let itemTemp = 1;
        for (let i = 0; i < fileListArray.length; i++) {
            if ((i + 1) > items) {
                break;
            }
            if (fileListArray[i] == null) {
                continue;
            }
            if (percent == 100) {
                WpFileUploadTools.setFileItemProgress(wfu, i, 100, 0);
                continue;
            }
            if (itemTemp < items) {
                itemTemp++;
                bytesRead = bytesRead - fileListArray[i].size;
                WpFileUploadTools.setFileItemProgress(wfu, i, 100, 0);
            } else if (itemTemp == items) {
                let fileItemPercent = WpFileUploadComputer.mul(WpFileUploadComputer.div(bytesRead, fileListArray[i].size), 100);
                if (fileItemPercent == 100) {
                    itemTemp++;
                    bytesRead = bytesRead - fileListArray[i].size;
                }
                WpFileUploadTools.setFileItemProgress(wfu, i, fileItemPercent, 0);
            }
        }
    },
    "setFileItemProgress": function(wfu, fileCodeId, percent, status) {
        if (!wfu.showFileItemProgress) {
            return;
        }
        let uploadId = wfu.uploadId;
        let fileItemView = WpFileItemTools.getNeedUploadItem(uploadId, fileCodeId);
        let fileItemProgress = WpFileItemTools.getFileViewProgress(fileItemView);
        let fileItemStatus = WpFileItemTools.getFileViewStatus(fileItemView);
        if (status == 1) {
            if (wfu.showFileItemProgress) {
                fileItemProgress.addClass("error");
                fileItemProgress.css("width", "100%");
            }
            fileItemStatus.addClass("iconfont icon-cha");
        } else if (status == 0) {
            if (wfu.showFileItemProgress) {
                fileItemProgress.css("width", percent + "%");
            }
            if (percent == 100) {
                fileItemStatus.off();
                fileItemStatus.addClass("iconfont icon-gou");
            }
        }
    },
    "uploadError": function() {
        let wfu = this;
        let uploadId = wfu.uploadId;
        if (wfu.showFileItemProgress) {
            let progressObj = WpFileItemTools.getNeedUploadItemArray(uploadId).find(".progress>div");
            progressObj.addClass("error");
            progressObj.css("width", "100%");
        } else {
            WpFileUploadTools.changeUploadButtonsStatus(wfu, 2);
        }
        let fileItemView = WpFileItemTools.getNeedUploadItemArray(uploadId);
        WpFileItemTools.getFileViewStatus(fileItemView).addClass("iconfont icon-cha");
        WpFileUploadTools.setProgressNumber(wfu, 0);
    },
    "uploadSuccess": function() {
        let wfu = this;
        WpFileUploadTools.setSuccessOfSubmit(wfu);
    },
    "changeUploadButtonsStatus": function(wfu, status) {
        if (status == 0) {
            WpFileUploadTools.disableFileUpload(wfu);
            WpFileUploadTools.disableCleanFile(wfu);
            WpFileUploadTools.disableFileSelect(wfu);
        } else if (status == 1) {
            if (wfu.isAutoClean) {
                WpFileUploadEvent.cleanFileEvent(wfu);
            } else {
                WpFileUploadEvent.initWithCleanFile(wfu);
                WpFileUploadEvent.initWithUpload(wfu);
                WpFileUploadEvent.initWithSelectFile(wfu);
                WpFileUploadEvent.stopUpload(wfu);
            }
        } else if (status == 2) {
            WpFileUploadEvent.initWithCleanFile(wfu);
            WpFileUploadEvent.initWithUpload(wfu);
            WpFileUploadEvent.initWithSelectFile(wfu);
            WpFileUploadEvent.stopUpload(wfu);
        }
    },
    "disableFileSelect": function(wfu) {
        let uploadId = wfu.uploadId;
        let selectFileBt = $("#" + uploadId + " .uploadBts .selectFileBt");
        selectFileBt.css("background-color", "#DDDDDD");
        selectFileBt.off();
    },
    "disableFileUpload": function(wfu) {
        if (!wfu.isHiddenUploadBt) {
            let uploadId = wfu.uploadId;
            let uploadFileBt = $("#" + uploadId + " .uploadBts .uploadFileBt");
            let uploadFileBtIcon = $("#" + uploadId + " .uploadBts .uploadFileBt i");
            uploadFileBt.off();
            uploadFileBtIcon.css("color", "#DDDDDD");
        }
    },
    "disableCleanFile": function(wfu) {
        if (!wfu.isHiddenCleanBt) {
            let uploadId = wfu.uploadId;
            let cleanFileBt = $("#" + uploadId + " .uploadBts .cleanFileBt");
            let cleanFileBtIcon = $("#" + uploadId + " .uploadBts .cleanFileBt i");
            cleanFileBt.off();
            cleanFileBtIcon.css("color", "#DDDDDD");
        }
    },
    "setSuccessOfSubmit": function(wfu) {
        let progressUrl = wfu.progressUrl;
        if (!wfu.scheduleStandard && (progressUrl == null || progressUrl == "" || progressUrl === "#")) {
            let bytesRead = WpFileUploadFileList.getFilesDataAmount(wfu);
            WpFileUploadTools.showUploadProgress(wfu, bytesRead, 100);
        }
    },
    "getFileNameWithUrl": function(fileUrl) {
        let index = fileUrl.lastIndexOf("/");
        if (index <= 0) {
            index = fileUrl.lastIndexOf("\\");
        }
        index = index + 1;
        let fileName = fileUrl.substring(index, fileUrl.length);
        return fileName;
    }
};
let WpFileUploadMessage = {
    "error": function(message) {
        console.error(message)
    },
    "info": function(message) {
        console.info(message);
    },
    "warn": function(message) {
        console.warn(message);
    }
};
let WpFileUploadMessageModel = {
    "notExitUploadId": "要设定一个容器id!",
    "initConfigFormatError": "初始化参数错误:参数类型应该为对象类型，如:{\"uploadUrl\":\"xxxxxx\"}",
    "outMaxFileNumber": function(maxFileNumber) {
        return "文件数量错误：最多只能上传" + maxFileNumber + "个文件"
    },
    "fileIsExit": function(fileName) {
        return "重复上传文件错误：文件（" + fileName + "）已经存在！";
    },
    "fileIsHaveUpload": function(fileName) {
        return "多次上传文件错误：文件（" + fileName + "）已经上传过，不能再次上传！";
    },
    "outOfTotalSize": function(totalSize) {
        return "文件总大小限制错误：文件加入已超出总大小限制！请控制在" + totalSize + "KB以内，或多次上传";
    },
    "outOfSize": function(fileName, fileSizeLimit) {
        return "文件大小限制错误：文件（" + fileName + "）超出了大小限制！请控制在" + fileSizeLimit + "KB内"
    },
    "notSupperFileType": function(fileName) {
        return "文件格式错误：不支持该格式文件上传:" + fileName;
    },
    "noFileUpload": "没有要上传的文件！",
    "noUploadUrl": "没有上传的地址，进行模拟上传......"
};
let WpFileUploadComputer = {
    "add": function(a, b) {
        let c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
        return e = Math.pow(10, Math.max(c, d)),
        (WpFileUploadComputer.mul(a, e) + WpFileUploadComputer.mul(b, e)) / e;
    },
    "sub": function(a, b) {
        let c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
        return e = Math.pow(10, Math.max(c, d)),
        (WpFileUploadComputer.mul(a, e) - WpFileUploadComputer.mul(b, e)) / e;
    },
    "mul": function(a, b) {
        var c = 0
          , d = a.toString()
          , e = b.toString();
        try {
            c += d.split(".")[1].length;
        } catch (f) {}
        try {
            c += e.split(".")[1].length;
        } catch (f) {}
        return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
    },
    "div": function(a, b) {
        let c, d, e = 0, f = 0;
        try {
            e = a.toString().split(".")[1].length;
        } catch (g) {}
        try {
            f = b.toString().split(".")[1].length;
        } catch (g) {}
        return c = Number(a.toString().replace(".", "")),
        d = Number(b.toString().replace(".", "")),
        WpFileUploadComputer.mul(c / d, Math.pow(10, f - e));
    }
};
let WpFileUploadFormTools = {
    "getFormData": function(formId) {
        let formData = {};
        let $form = $("#" + formId);
        let input_doms = $form.find("input[name][ignore!='true'],textarea[name][ignore!='true']");
        let select_doms = $form.find("select[name][ignore!='true']");
        for (let i = 0; i < input_doms.length; i++) {
            let inputItem = input_doms.eq(i);
            let inputName = "";
            if (inputItem.attr("type") == "radio") {
                if (inputItem.is(":checked")) {
                    inputName = inputItem.attr("name");
                    formData[inputName] = $.trim(inputItem.val());
                }
            } else {
                inputName = inputItem.attr("name");
                formData[inputName] = $.trim(inputItem.val());
            }
        }
        for (let j = 0; j < select_doms.length; j++) {
            let selectItem = select_doms.eq(j);
            let selectName = selectItem.attr("name");
            formData[selectName] = $.trim(selectItem.val());
        }
        return formData;
    },
    "getFormDataOfUploadFile": function(formId) {
        let formData = [];
        let $form = $("#" + formId);
        let input_doms = $form.find("input[name][ignore!='true'],textarea[name][ignore!='true']");
        let select_doms = $form.find("select[name][ignore!='true']");
        for (let i = 0; i < input_doms.length; i++) {
            let inputItem = input_doms.eq(i);
            let inputName = "";
            if (inputItem.attr("type") == "radio") {
                if (inputItem.is(":checked")) {
                    inputName = inputItem.attr("name");
                    formData[formData.length] = {
                        "name": inputName,
                        "value": $.trim(inputItem.val())
                    }
                }
            } else {
                inputName = inputItem.attr("name");
                formData[formData.length] = {
                    "name": inputName,
                    "value": $.trim(inputItem.val())
                }
            }
        }
        for (let j = 0; j < select_doms.length; j++) {
            let selectItem = select_doms.eq(j);
            let selectName = selectItem.attr("name");
            formData[formData.length] = {
                "name": selectName,
                "value": $.trim(selectItem.val())
            }
        }
        return formData;
    }
};
let WpFileItemTools = {
    "getNeedUploadItemArray": function(uploadId) {
        return $("#" + uploadId + " .box .fileItem[fileCodeId]");
    },
    "getIsUploadItemArray": function(uploadId) {
        return $("#" + uploadId + " .box .fileItem[showFileCode]");
    },
    "getNeedUploadItem": function(uploadId, fileId) {
        return $("#" + uploadId + " .box .fileItem[fileCodeId='" + fileId + "']");
    },
    "getIsUploadItem": function(uploadId, fileId) {
        return $("#" + uploadId + " .box .fileItem[showFileCode='" + fileId + "']");
    },
    "getFileViewArray": function(uploadId) {
        return $("#" + uploadId + " .box .fileItem");
    },
    "getFileViewProgress": function(viewObj) {
        return viewObj.find(".progress>div");
    },
    "getFileViewStatus": function(viewObj) {
        return viewObj.find(".status>i");
    },
    "getFileViewRemove": function(viewObj) {
        return viewObj.find(".status .icon-shanchu");
    },
    "getFileViewDown": function(viewObj) {
        return viewObj.find(".down .icon-xiazai");
    }
};
