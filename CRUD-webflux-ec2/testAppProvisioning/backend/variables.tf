variable "vpc_id" {
    type    = "string"
    default = "vpc-3f1a3758"
}

variable "ami_id" {
    type    = "string"
    # TODO: Try and get latest AMI rather than hardcoded ID
    default = "ami-0c1d8842b9bfc767c"
}

variable "key_pair_name" {
    type    = "string"
    default = "travis"
}