terraform {
    backend "s3" {
        bucket = "sebs-terraform-state"
        key    = "testapp"
        region = "ap-southeast-2"
    }
}

provider "aws" {
    region = "ap-southeast-2"

    # May be better to assume a role but for now will use the key/secret access key
    # assume_role {
    #     role_arn = "INSERT ROLE HERE"
    # }
}