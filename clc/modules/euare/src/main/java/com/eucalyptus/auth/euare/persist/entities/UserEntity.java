/*************************************************************************
 * Copyright 2009-2015 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Software License Agreement (BSD License)
 *
 *   Copyright (c) 2008, Regents of the University of California
 *   All rights reserved.
 *
 *   Redistribution and use of this software in source and binary forms,
 *   with or without modification, are permitted provided that the
 *   following conditions are met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the
 *     distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *   ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 *   THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 *   COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 *   AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 *   IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 *   SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 *   WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 *   REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 *   IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 *   NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.auth.euare.persist.entities;

import static com.eucalyptus.upgrade.Upgrades.Version.v4_2_0;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.eucalyptus.auth.util.Identifiers;
import com.eucalyptus.component.id.Euare;
import com.eucalyptus.entities.AbstractPersistent;
import com.eucalyptus.upgrade.Upgrades;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import groovy.sql.Sql;

/**
 * Database entity for a user.
 */
@Entity
@PersistenceContext( name = "eucalyptus_auth" )
@Table( name = "auth_user" )
@Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
public class UserEntity extends AbstractPersistent implements Serializable {

  @Transient
  private static final long serialVersionUID = 1L;
  
  // The User ID the user facing group id which conforms to length and character restrictions per spec.
  @Column( name = "auth_user_id_external" )
  String userId;

  // User name
  @Column( name = "auth_user_name" )
  String name;
  
  // User path (prefix to organize user name space, see AWS spec)
  @Column( name = "auth_user_path" )
  String path;
  
  // Flag to control the activeness of a user.
  @Column( name = "auth_user_is_enabled" )
  Boolean enabled;
  
  // Web session token
  @Column( name = "auth_user_token" )
  String token;

  // User registration confirmation code
  @Column( name = "auth_user_confirmation_code" )
  String confirmationCode;
  
  // Web login password
  @Column( name = "auth_user_password" )
  String password;

  // Time when password expires
  @Column( name = "auth_user_password_expires" )
  Long passwordExpires;
  
  @Column( name = "auth_user_unique_name", unique = true )
  String uniqueName;
  
  // List of secret keys
  @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "user" )
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  List<AccessKeyEntity> keys;
  
  // List of certificates
  @OneToMany( cascade = { CascadeType.ALL }, mappedBy = "user" )
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  List<CertificateEntity> certificates;
  
  // Customizable user info in key-value pairs
  @ElementCollection
  @CollectionTable( name = "auth_user_info_map" )
  @MapKeyColumn( name = "auth_user_info_key" )
  @Column( name = "auth_user_info_value" )
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  Map<String, String> info;
  
  // User's groups
  @ManyToMany( fetch = FetchType.LAZY, mappedBy="users" ) // not owning side
  @Cache( usage = CacheConcurrencyStrategy.TRANSACTIONAL )
  List<GroupEntity> groups;

  
  public UserEntity( ) {
    this.keys = Lists.newArrayList( );
    this.certificates = Lists.newArrayList( );
    this.info = Maps.newHashMap( );
    this.groups = Lists.newArrayList( );
  }

  public UserEntity( final String accountId, final String name ) {
    this( );
    this.name = name;
    this.uniqueName = String.format("%s:%s", accountId, name);
  }
  
  public UserEntity( Boolean enabled ) {
    this( );
    this.enabled = enabled;
  }
  
  @PrePersist
  public void generateOnCommit() {
    if( this.userId == null ) {/** NOTE: first time that user is committed it needs to generate its own ID (i.e., not the database id), do this at commit time and generate if null **/
      this.userId = Identifiers.generateIdentifier( "AID" );
    }
  }
  
  public static UserEntity newInstanceWithUserId( final String userId ) {
    UserEntity u = new UserEntity( );
    u.userId = userId;
    return u;
  }
  
  @Override
  public boolean equals( final Object o ) {
    if ( this == o ) return true;
    if ( o == null || getClass( ) != o.getClass( ) ) return false;
    
    UserEntity that = ( UserEntity ) o;    
    if ( !name.equals( that.name ) ) return false;
    
    return true;
  }

  @Override
  public String toString( ) {
    StringBuilder sb = new StringBuilder( );
    sb.append( "User(" );
    sb.append( "ID=" ).append( this.getId( ) ).append( ", " );
    sb.append( "UserID=" ).append( this.getUserId( ) ).append( ", " );
    sb.append( "name=" ).append( this.getName( ) ).append( ", " );
    sb.append( "path=" ).append( this.getPath( ) ).append( ", " );
    sb.append( "enabled=" ).append( this.isEnabled( ) ).append( ", " );
    sb.append( "passwordExpires=" ).append( this.getPasswordExpires( ) );
    sb.append( ")" );
    return sb.toString( );
  }

  public String getName( ) {
    return this.name;
  }
  
  public void setName( String name ) {
    this.name = name;
    if (this.uniqueName!=null && this.uniqueName.indexOf(":")>0) {
      final String[] tokens = this.uniqueName.split(":");
      final String accountId = tokens[0];
      this.uniqueName = String.format("%s:%s", accountId, name);
    }
  }
  
  public String getPath( ) {
    return this.path;
  }
  
  public void setPath( String path ) {
    this.path = path;
  }
  
  public Boolean isEnabled( ) {
    return this.enabled;
  }
  
  public void setEnabled( Boolean enabled ) {
    this.enabled = enabled;
  }
  
  public String getToken( ) {
    return this.token;
  }
  
  public void setToken( String token ) {
    this.token = token;
  }
  
  public String getConfirmationCode( ) {
    return this.confirmationCode;
  }
  
  public void setConfirmationCode( String confirmationCode ) {
    this.confirmationCode = confirmationCode;
  }
  
  public String getPassword( ) {
    return this.password;
  }
  
  public void setPassword( String password ) {
    this.password = password;
  }
  
  public Long getPasswordExpires( ) {
    return this.passwordExpires;
  }
  
  public void setPasswordExpires( Long passwordExpires ) {
    this.passwordExpires = passwordExpires;
  }
  
  public List<AccessKeyEntity> getKeys( ) {
    return this.keys;
  }
  
  public List<CertificateEntity> getCertificates( ) {
    return this.certificates;
  }
  
  public Map<String, String> getInfo( ) {
    return this.info;
  }
  
  public List<GroupEntity> getGroups( ) {
    return this.groups;
  }

  public String getUserId( ) {
    return this.userId;
  }

  @Upgrades.PreUpgrade( value = Euare.class, since = v4_2_0 )
  public static class UserPreUpgrade420 implements Callable<Boolean> {
    private static final Logger logger = Logger.getLogger( UserPreUpgrade420.class );

    @Override
    public Boolean call( ) throws Exception {
      Sql sql = null;
      try {
        sql = Upgrades.DatabaseFilters.NEWVERSION.getConnection("eucalyptus_auth");
        sql.execute( "update auth_user set auth_user_is_enabled = false where auth_user_name = 'admin' and not auth_user_reg_stat = 'CONFIRMED'" );
        sql.execute( "alter table auth_user drop column if exists auth_user_reg_stat" );
        return true;
      } catch (Exception ex) {
        if ( ex.getMessage( ) != null && ex.getMessage( ).contains( "auth_user_reg_stat" ) ) {
          logger.info( "Skipping account status cleanup, changes already applied" );
          return true;
        } else {
          logger.error( ex, ex );
          return false;
        }
      } finally {
        if (sql != null) {
          sql.close();
        }
      }
    }
  }
}
